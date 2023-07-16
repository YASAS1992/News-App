package com.example.newsapp.fragments


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.utill.State
import com.example.newsapp.activities.MainActivity
import com.example.newsapp.adapters.CategoryAdapter
import com.example.newsapp.adapters.HeadlineAdapter
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var headlineAdapter: HeadlineAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var newsAdapter: NewsAdapter
    lateinit var mainActivity: MainActivity

    companion object fun newInstance() : HomeFragment? {
        return HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        headlineAdapter = HeadlineAdapter(requireContext())
        categoryAdapter = CategoryAdapter(requireContext(),viewModel){

        }
        newsAdapter = NewsAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        mainActivity = activity as MainActivity
        mainActivity.hideKeyBoard(binding.root)
        binding.tvSearch.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.tvLatestNews.visibility = GONE
                binding.btnSeeAll.visibility = GONE
                binding.rvHeadlines.visibility = GONE
                binding.rvCategories.visibility = GONE
                binding.ivClose.visibility = VISIBLE
            } else {
                binding.tvLatestNews.visibility = VISIBLE
                binding.btnSeeAll.visibility = VISIBLE
                binding.rvHeadlines.visibility = VISIBLE
                binding.rvCategories.visibility = VISIBLE
                binding.ivClose.visibility = GONE
            }
        }

        binding.tvSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text is being changed
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.getNewsSearch(s.toString())
            }
        }
        )

        binding.ivClose.setOnClickListener {
            binding.tvSearch.clearFocus()
            viewModel.getAllNews()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiateRecyclerViews()
        viewModel.getHeadlines()
        observeHeadlines()
        populateCategories()
        observeCategory()
        observeAllNews()
    }

    private fun observeCategory() {
        viewModel.category.observe(viewLifecycleOwner, Observer {
            viewModel.getAllNews()
        })
    }

    private fun observeAllNews() {
        viewModel.all_news.observe(viewLifecycleOwner, Observer {
            when(it){
                is State.Error -> {

                }
                is State.Loading -> {

                }
                is State.Success -> {
                    newsAdapter.setData(it.response!!.articles)
                }
            }
        })
    }

    private fun populateCategories() {
        categoryAdapter.setData(viewModel.getCategories())
        viewModel.getNewsByCategory("business")
    }

    private fun observeHeadlines() {
        viewModel.headlines.observe(viewLifecycleOwner, Observer {
            when(it){
                is State.Error -> {

                }
                is State.Loading -> {

                }
                is State.Success -> {
                    headlineAdapter.setData(it.response!!.articles)
                }
            }
        })
    }

    private fun initiateRecyclerViews(){
        binding.rvHeadlines.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter = headlineAdapter
        }

        binding.rvCategories.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter = categoryAdapter
        }

        binding.rvLatest.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            adapter = newsAdapter
        }
    }
}
package com.example.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.activities.MainActivity
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.data.Article
import com.example.newsapp.databinding.FragmentFavoritesBinding
import com.example.newsapp.viewmodel.FavoritesViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.ArrayList


class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var newsAdapter: NewsAdapter
    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[FavoritesViewModel::class.java]
        newsAdapter = NewsAdapter(requireContext())
        mainActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiateRecyclerViews()
        populateFavorites()
    }

    private fun populateFavorites(){

        lifecycleScope.launch {
            mainActivity.getApp().settings!!.getUser().collect{
                viewModel.getAllNews(it!!).observe(viewLifecycleOwner, Observer { users ->
                    newsAdapter.setData(users as ArrayList<Article>)
                })
            }
        }


    }

    private fun initiateRecyclerViews(){
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            adapter = newsAdapter
        }
    }
}
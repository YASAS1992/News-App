package com.example.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.State
import com.example.newsapp.adapters.HeadlineAdapter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var headlineAdapter: HeadlineAdapter

    companion object fun newInstance() : HomeFragment? {
        return HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        headlineAdapter = HeadlineAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiateRecyclerViews()
        viewModel.getHeadlines()
        observeHeadlines()
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
    }
}
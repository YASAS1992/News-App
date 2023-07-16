package com.example.newsapp.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.BaseActivity
import com.example.newsapp.R
import com.example.newsapp.adapters.UpdateAdapter
import com.example.newsapp.databinding.ActivityHotUpdateBinding
import com.example.newsapp.utill.State
import com.example.newsapp.viewmodel.HotUpdatesViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotUpdateActivity : BaseActivity() {

    lateinit var binding : ActivityHotUpdateBinding
    private lateinit var updateAdapter: UpdateAdapter
    private val viewModel : HotUpdatesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateAdapter = UpdateAdapter(this)
        initiateRecyclerViews()
        viewModel.getAllUpdate()
        observeUpdate()

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun observeUpdate(){
        viewModel.updates.observe(this, Observer {
            when(it){
                is State.Error -> {
                    Snackbar.make(binding.root, R.string.something_went_wrong, Snackbar.LENGTH_SHORT)
                        .show()
                }
                is State.Loading -> {

                }
                is State.Success -> {
                    updateAdapter.setData(it.response!!.articles)
                }
            }
        })
    }

    private fun initiateRecyclerViews(){

        binding.rvUpdates.apply {
            layoutManager = LinearLayoutManager(this@HotUpdateActivity, LinearLayoutManager.VERTICAL,false)
            adapter = updateAdapter
        }
    }
}
package com.example.newsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.newsapp.R
import com.example.newsapp.State
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()

        viewModel.getTopHeadlines()

        viewModel.topHeadlines.observe(this, Observer {
            when(it){
                is State.Error -> {
                    Log.e("API","ERROR")
                }
                is State.Loading -> {
                    Log.e("API","LOADING")
                }
                is State.Success -> {
                    Log.e("API", it.response!!.articles[0].content)
                }
            }
        })
    }

    private fun setupBottomNavigation(){
        val bottomNavigation = binding.btmNav
        var navController = Navigation.findNavController(this, R.id.host_fragment)
        NavigationUI.setupWithNavController(bottomNavigation,navController)
    }
}
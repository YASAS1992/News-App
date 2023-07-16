package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.State
import com.example.newsapp.data.Article
import com.example.newsapp.data.HeadlinesResponse
import com.example.newsapp.repository.FavoritesRepository
import com.example.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel
@Inject
constructor(private val favoritesRepository : FavoritesRepository,
            application: Application
): AndroidViewModel(application){

    fun getAllNews() = favoritesRepository.getSavedNews()

}
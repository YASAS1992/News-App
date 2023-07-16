package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.newsapp.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel
@Inject
constructor(private val favoritesRepository : FavoritesRepository,
            application: Application
): AndroidViewModel(application){

    fun getAllNews(user:String) = favoritesRepository.getSavedNews(user)

}
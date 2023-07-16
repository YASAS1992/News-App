package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.Article
import com.example.newsapp.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject
constructor(private val favoritesRepository: FavoritesRepository,
                    application: Application
): AndroidViewModel(application) {

    fun saveNewsArticle(article: Article,user:String) = viewModelScope.launch {
        article.user = user
        favoritesRepository.insertFavorite(article)
    }

}
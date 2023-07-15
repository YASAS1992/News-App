package com.example.newsapp.repository

import com.example.newsapp.retrofit.NewsApi
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi
) {
    suspend fun getHeadlines() =
        newsApi.getTopHeadlines()
}
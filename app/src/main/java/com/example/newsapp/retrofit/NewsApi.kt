package com.example.newsapp.retrofit

import com.example.newsapp.AppConstants
import com.example.newsapp.data.HeadlinesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi  {

    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = AppConstants.API_KEY
    ): Response<HeadlinesResponse>

    @GET("everything")
    suspend fun getAllNews(
        @Query("q") query: String = "",
        @Query("apiKey") apiKey: String = AppConstants.API_KEY
    ): Response<HeadlinesResponse>

}
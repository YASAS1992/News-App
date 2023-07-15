package com.example.newsapp.retrofit

import com.example.newsapp.data.HeadlinesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi  {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "fbf190a3c3c64a928d2666901df2c5ea"
    ): Response<HeadlinesResponse>

}
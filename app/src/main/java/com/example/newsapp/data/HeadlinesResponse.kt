package com.example.newsapp.data

data class HeadlinesResponse(
    val articles: ArrayList<Article>,
    val status: String,
    val totalResults: Int
)
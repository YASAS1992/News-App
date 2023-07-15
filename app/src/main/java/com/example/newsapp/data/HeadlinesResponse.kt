package com.example.newsapp.data

data class HeadlinesResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
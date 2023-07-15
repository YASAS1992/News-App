package com.example.newsapp

sealed class State<T>(
    val response: T? = null,
    val error: String? = null
) {
    class Success<T>(response: T) : State<T>(response)
    class Error<T>(error: String?, response: T? = null) : State<T>(response, error)
    class Loading<T> : State<T>()
}
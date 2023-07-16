package com.example.newsapp.repository

import com.example.newsapp.data.User
import com.example.newsapp.db.ArticleDatabase
import javax.inject.Inject

class UserRepository @Inject
constructor(
    private val db: ArticleDatabase
) {
    suspend fun registerUser(user: User) = db.userDao().insertUser(user)

    fun loginUser(username:String) = db.userDao().getUser(username)

    fun getAllUsers() = db.userDao().getAllUser()

    fun getUser(user:String) = db.userDao().getUser(user)
}
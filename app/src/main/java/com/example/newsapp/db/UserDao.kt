package com.example.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.newsapp.data.ArticleWithUser
import com.example.newsapp.data.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
    fun getUser(username:String): LiveData<List<User>>

    @Query("SELECT * FROM users")
    fun getAllUser(): LiveData<List<User>>

    @Transaction
    @Query("SELECT * FROM users")
    fun getArticleWithUser(): LiveData<List<ArticleWithUser>>
}
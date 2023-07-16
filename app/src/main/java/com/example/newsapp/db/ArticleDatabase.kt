package com.example.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.AppConstants
import com.example.newsapp.data.Article
import com.example.newsapp.data.User

@Database(entities = [Article::class,User::class], version = 3, exportSchema = false)
@TypeConverters(TypeConvertor::class)
abstract class ArticleDatabase : RoomDatabase(){

    abstract fun articleDao():ArticleDao

    abstract fun userDao():UserDao

    companion object {

        @Volatile
        var INSTANCE : ArticleDatabase? = null

        @Synchronized
        fun getInstance(context: Context):ArticleDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    ArticleDatabase::class.java,
                    AppConstants.USER_DB
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as ArticleDatabase
        }
    }
}
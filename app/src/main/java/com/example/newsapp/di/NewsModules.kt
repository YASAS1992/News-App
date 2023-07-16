package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.AppConstants
import com.example.newsapp.AppSettings
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.retrofit.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModules {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext appContext: Context): ArticleDatabase {
        return ArticleDatabase.getInstance(appContext)
    }

//    @Provides
//    @Singleton
//    fun provideNewsDataStore(@ApplicationContext appContext: Context): AppSettings {
//        return AppSettings(appContext)
//    }

}
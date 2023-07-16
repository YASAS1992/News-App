package com.example.newsapp.repository

import com.example.newsapp.data.Article
import com.example.newsapp.db.ArticleDatabase
import javax.inject.Inject

class FavoritesRepository
@Inject
constructor(
    private val db: ArticleDatabase
) {
    suspend fun insertFavorite(article: Article) = db.articleDao().upsertArticle(article)

    fun getSavedNews(user:String) = db.articleDao().getAllArticles(user)
}
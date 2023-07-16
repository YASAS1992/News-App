package com.example.newsapp.data

import androidx.room.Embedded
import androidx.room.Relation

data class ArticleWithUser (
    @Embedded val user: User,
    @Relation(
        parentColumn = "username",
        entityColumn = "user"
    )
    val article: Article
    )
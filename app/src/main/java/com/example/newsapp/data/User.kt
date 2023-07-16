package com.example.newsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User(
    @PrimaryKey
    var username: String,
    var email: String,
    var password: String
)
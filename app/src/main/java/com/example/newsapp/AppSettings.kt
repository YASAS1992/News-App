package com.example.newsapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class AppSettings(
    val context: Context,
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data")

    companion object {
        val USER = stringPreferencesKey("USER")
    }

    suspend fun putUser(username:String){
        context.dataStore.edit {
            it[USER] = username?:""
        }
    }

    suspend fun getUser()  = context.dataStore.data.map {
        it[USER]
    }

    suspend fun removeUser(){
        context.dataStore.edit {
            it[USER] = ""
        }
    }
}
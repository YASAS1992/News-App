package com.example.newsapp

import android.app.Application
import com.example.newsapp.repository.UserRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class NewsApplication : Application(){
    var settings: AppSettings? = null
        get() {
            if (field == null) {
                field = AppSettings(this)
            }
            return field
        }
        private set
}
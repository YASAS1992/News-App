package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.activities.InitActivity
import com.example.newsapp.activities.MainActivity
import dagger.hilt.android.AndroidEntryPoint

 open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

     fun getApp():NewsApplication{
         return application as NewsApplication
     }

     fun launchInit(){
         val intent = Intent(this, InitActivity::class.java)
         startActivity(intent)
         finish()
     }

     fun launchMain(){
         val intent = Intent(this, MainActivity::class.java)
         startActivity(intent)
         finish()
     }
}
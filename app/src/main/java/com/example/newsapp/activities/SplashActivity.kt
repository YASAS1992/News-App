package com.example.newsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.BaseActivity
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivitySplashBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
           init()
        }, 3000)
    }

    private fun init(){
        lifecycleScope.launch {
            getApp().settings!!.getUser().collect{
                if (it == null || it == ""){
                    withContext(Dispatchers.Main) {
                        launchInit()
                    }
                }else{
                    launchMain()
                }
            }
        }

    }


}

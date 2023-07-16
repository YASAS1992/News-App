package com.example.newsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.BaseActivity
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityInitBinding

class InitActivity : BaseActivity() {

    lateinit var binding: ActivityInitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val i = Intent(this,LoginActivity::class.java)
            startActivity(i)
        }

        binding.btnRegister.setOnClickListener {

            val i = Intent(this,RegisterActivity::class.java)
            startActivity(i)

        }
    }
}
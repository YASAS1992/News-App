package com.example.newsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.newsapp.BaseActivity
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityRegisterBinding
import com.example.newsapp.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity() {

    lateinit var binding: ActivityRegisterBinding
    private val viewModel : RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etLogin.setOnClickListener {
            val i = Intent(this,LoginActivity::class.java)
            startActivity(i)
        }

        binding.btnRegister.setOnClickListener {
            viewModel.validateFields(binding.etUsername.text.toString(),binding.etEmail.text.toString(),binding.etPassword.text.toString(),binding.etConfPassword.text.toString())
        }

        viewModel.user_name_validation.observe(this, Observer {
            binding.etUsername.error = it
        })
        viewModel.email_validation.observe(this, Observer {
            binding.etEmail.error = it
        })
        viewModel.password_validation.observe(this, Observer {
            binding.etPassword.error = it
        })
        viewModel.conf_password_validation.observe(this, Observer {
            binding.etConfPassword.error = it
        })
        viewModel.register_success.observe(this, Observer {
            Snackbar.make(binding.root, R.string.register_success, Snackbar.LENGTH_SHORT)
                .show()

            val i = Intent(this,LoginActivity::class.java)
            startActivity(i)
        })
    }
}
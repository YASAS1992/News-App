package com.example.newsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.BaseActivity
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityLoginBinding
import com.example.newsapp.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    lateinit var binding:ActivityLoginBinding
    private val viewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideKeyBoard(binding.root)
        binding.etRegister.setOnClickListener {
            val i = Intent(this,RegisterActivity::class.java)
            startActivity(i)
        }

        binding.btnLogin.setOnClickListener {
            viewModel.validateFields(binding.etUsername.text.toString(),binding.etPassword.text.toString())
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

        viewModel.user_name_validation.observe(this, Observer {
            binding.etUsername.error = it
        })

        viewModel.password_validation.observe(this, Observer {
            binding.etPassword.error = it
        })

        viewModel.validation.observe(this, Observer {
            viewModel.loginUser(binding.etUsername.text.toString()).observe(this, Observer {
                if(it != null && it.isNotEmpty()){
                    viewModel.validateUser(binding.etUsername.text.toString(),binding.etPassword.text.toString(),it)
                }else{
                    Snackbar.make(binding.root, R.string.no_user_found, Snackbar.LENGTH_SHORT)
                        .show()
                }
            })
        })

        viewModel.login_success.observe(this, Observer {

            lifecycleScope.launch {
                getApp().settings!!.putUser(it.username)
            }

            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        })

        viewModel.login_failed.observe(this, Observer {
            if(it){
                Snackbar.make(binding.root, R.string.invalid_username_or_password, Snackbar.LENGTH_SHORT)
                    .show()
            }
        })
    }
}
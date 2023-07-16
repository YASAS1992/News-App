package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.Article
import com.example.newsapp.data.User
import com.example.newsapp.repository.FavoritesRepository
import com.example.newsapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
@Inject
constructor(private val userRepository: UserRepository,
            application: Application
): AndroidViewModel(application) {


    private val _user_name_validation: MutableLiveData<String> = MutableLiveData()
    val user_name_validation: LiveData<String> = _user_name_validation

    private val _email_validation: MutableLiveData<String> = MutableLiveData()
    val email_validation: LiveData<String> = _email_validation

    private val _password_validation: MutableLiveData<String> = MutableLiveData()
    val password_validation: LiveData<String> = _password_validation

    private val _conf_password_validation: MutableLiveData<String> = MutableLiveData()
    val conf_password_validation: LiveData<String> = _conf_password_validation

    private val _register_success: MutableLiveData<Boolean> = MutableLiveData()
    val register_success: LiveData<Boolean> = _register_success

    fun validateFields(username:String?,email:String?,password:String?,confPassword:String?){
        if(username == null || username == ""){
            _user_name_validation.postValue("Please enter username.")
        }else if(email == null || email == ""){
            _email_validation.postValue("Please enter email.")
        }else if(password == null || password == ""){
            _password_validation.postValue("Please enter password.")
        }else if(confPassword == null || confPassword == ""){
            _conf_password_validation.postValue("Please enter password confirmation")
        }else if(password != confPassword ){
            _conf_password_validation.postValue("Password confirmation mismatch.")
        }else{
            registerUser(User(username,email,password))
        }
    }

    private fun registerUser(user: User) = viewModelScope.launch {
        userRepository.registerUser(user)
        _register_success.postValue(true)
    }

}
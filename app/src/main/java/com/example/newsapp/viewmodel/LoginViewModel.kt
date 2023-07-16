package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.AppSettings
import com.example.newsapp.data.User
import com.example.newsapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(private val userRepository: UserRepository,
            application: Application
): AndroidViewModel(application) {

    private val _user_name_validation: MutableLiveData<String> = MutableLiveData()
    val user_name_validation: LiveData<String> = _user_name_validation

    private val _password_validation: MutableLiveData<String> = MutableLiveData()
    val password_validation: LiveData<String> = _password_validation

    private val _validation: MutableLiveData<Boolean> = MutableLiveData()
    val validation: LiveData<Boolean> = _validation

//    private val _login: MutableLiveData<List<User>> = MutableLiveData()
//    val login: LiveData<List<User>> = _login

    private val _login_success: MutableLiveData<User> = MutableLiveData()
    val login_success: LiveData<User> = _login_success

    private val _login_failed: MutableLiveData<Boolean> = MutableLiveData()
    val login_failed: LiveData<Boolean> = _login_failed


    fun validateFields(username:String?,password:String?){
        if(username == null || username == ""){
            _user_name_validation.postValue("Please enter username.")
        }else if(password == null || password == ""){
            _password_validation.postValue("Please enter password.")
        }else{
            _validation.postValue(true)
        }
    }

    fun loginUser(username: String) = userRepository.loginUser(username)

    fun validateUser(userName: String, password: String, users: List<User>?) {
        for(user in users!!){
            if(user.username == userName && user.password == password){
                _login_success.postValue(user)
                return
            }
        }
        _login_failed.postValue(true)
    }

}
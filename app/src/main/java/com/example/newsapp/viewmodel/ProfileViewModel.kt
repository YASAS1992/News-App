package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.newsapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject
constructor(private val userRepository: UserRepository,
            application: Application
): AndroidViewModel(application) {

    fun getUser(user:String) = userRepository.getUser(user)

}
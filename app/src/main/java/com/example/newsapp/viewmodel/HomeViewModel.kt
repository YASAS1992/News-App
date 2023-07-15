package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.State
import com.example.newsapp.data.HeadlinesResponse
import com.example.newsapp.repository.NewsRepository
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(private val newsRepository: NewsRepository,
            application: Application
): AndroidViewModel(application){

    private val _headlines: MutableLiveData<State<HeadlinesResponse>> = MutableLiveData()
    val headlines: LiveData<State<HeadlinesResponse>> = _headlines

    fun getHeadlines(){
        viewModelScope.launch{
            _headlines.postValue(State.Loading())
            _headlines.postValue(getStateMachedResponse(newsRepository.getHeadlines()))
        }
    }

    fun getCategories(){
        viewModelScope.launch {

        }
    }

    private fun getStateMachedResponse(response: Response<HeadlinesResponse>): State<HeadlinesResponse> {
        if (response.isSuccessful) {
            response.body()?.let { headlinesResponse ->
                return State.Success( headlinesResponse)
            }
        } else {
            return State.Error(
                getErrorMessage(response.errorBody()?.string())
            )
        }
        return State.Error(error = response.message())
    }

    private fun getErrorMessage(response: String?): String {
        val jobj: JsonObject = Gson().fromJson(response, JsonObject::class.java)
        return jobj["message"].toString()
    }

}
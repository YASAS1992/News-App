package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.HeadlinesResponse
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.utill.State
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HotUpdatesViewModel
@Inject
constructor(private val newsRepository: NewsRepository,
            application: Application
): AndroidViewModel(application) {

    private val _updates: MutableLiveData<State<HeadlinesResponse>> = MutableLiveData()
    val updates: LiveData<State<HeadlinesResponse>> = _updates

    fun getAllUpdate(){
        viewModelScope.launch{
            _updates.postValue(State.Loading())
            _updates.postValue(getStateMatchedResponse(newsRepository.getHeadlines()))
        }
    }

    private fun getStateMatchedResponse(response: Response<HeadlinesResponse>): State<HeadlinesResponse> {
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
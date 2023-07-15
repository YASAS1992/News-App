package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.State
import com.example.newsapp.data.Category
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

    private val _all_news: MutableLiveData<State<HeadlinesResponse>> = MutableLiveData()
    val all_news: LiveData<State<HeadlinesResponse>> = _all_news
    var category = "business"

    fun getHeadlines(){
        viewModelScope.launch{
            _headlines.postValue(State.Loading())
            _headlines.postValue(getStateMatchedResponse(newsRepository.getHeadlines()))
        }
    }

    fun getCategories() : ArrayList<Category>{
        var categories = ArrayList<Category>()
        categories.add(Category("Business","business"))
        categories.add(Category("Entertainment","entertainment"))
        categories.add(Category("General","general"))
        categories.add(Category("Health","health"))
        categories.add(Category("Science","science"))
        categories.add(Category("Sport","sport"))

        return categories
    }

    fun getAllNews(){
        viewModelScope.launch{
            _all_news.postValue(State.Loading())
            _all_news.postValue(getStateMatchedResponse(newsRepository.getAllNews(category)))
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
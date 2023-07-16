package com.example.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapp.R

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Log.e("API RES - >", "Start")
//        RetrofitInstance.api.getTopHeadlines("us","fbf190a3c3c64a928d2666901df2c5ea").enqueue(object : Callback<HeadlinesResponse>{
//            override fun onResponse(
//                call: Call<HeadlinesResponse>,
//                response: Response<HeadlinesResponse>
//            ) {
//                if(response.body() != null){
//                    Log.e("API RES - >", response.body()!!.articles[0].description)
//                }else{
//                    return
//                }
//            }
//
//            override fun onFailure(call: Call<HeadlinesResponse>, t: Throwable) {
//                Log.e("API RES - >", "FAILED")
//            }
//
//        })

    }

}
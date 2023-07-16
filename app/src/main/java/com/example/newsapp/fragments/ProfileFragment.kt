package com.example.newsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.activities.MainActivity
import com.example.newsapp.data.Article
import com.example.newsapp.databinding.FragmentProfileBinding
import com.example.newsapp.viewmodel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class ProfileFragment : Fragment() {

    lateinit var binding:FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    lateinit var mainActivity:MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        mainActivity = activity as MainActivity


        binding.btnLogout.setOnClickListener {
            lifecycleScope.launch {
                mainActivity.getApp().settings!!.removeUser()
                withContext(Dispatchers.Main) {
                    mainActivity.launchInit()
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUser()
    }


    fun getUser(){
        lifecycleScope.launch {
            mainActivity.getApp().settings!!.getUser().collect{
                viewModel.getUser(it!!).observe(viewLifecycleOwner, Observer { users ->
                    binding.tvUsername.text = "Username : ${users[0].username}"
                    binding.tvEmail.text = "Email : ${users[0].email}"
                })
            }
        }

    }

}
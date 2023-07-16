package com.example.newsapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.activities.InitActivity
import com.example.newsapp.activities.MainActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

     fun getApp():NewsApplication{
         return application as NewsApplication
     }

     fun launchInit(){
         val intent = Intent(this, InitActivity::class.java)
         startActivity(intent)
         finish()
     }

     fun launchMain(){
         val intent = Intent(this, MainActivity::class.java)
         startActivity(intent)
         finish()
     }

    open fun hideKeyBoard(view: View) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                hideSoftKeyboard(this@BaseActivity)
                false
            }
        }

        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                hideKeyBoard(innerView)
            }
        }
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (activity.currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        }
    }
}
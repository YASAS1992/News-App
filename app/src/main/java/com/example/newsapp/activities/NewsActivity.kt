package com.example.newsapp.activities

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.newsapp.BaseActivity
import com.example.newsapp.R
import com.example.newsapp.data.Article
import com.example.newsapp.databinding.ActivityNewsBinding
import com.example.newsapp.utill.TextFormatter
import com.example.newsapp.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsActivity : BaseActivity() {

    lateinit var binding: ActivityNewsBinding
    private val viewModel : NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var article = intent.getParcelableExtra<Article>("article")

        Glide.with(this)
            .load(article!!.urlToImage) // Replace with your image URL
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    binding.ivNews.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // This method can be empty
                }
            })

        binding.tvDate.text = TextFormatter.customDateFormatter(article.publishedAt,"yyyy-MM-dd'T'HH:mm:ss'Z'","EEEE, d MMMM yyyy")
        binding.tvTitle.text = article.title
        binding.tvAuthor.text = "${getString(R.string.published_by)} ${article.author }}"
        binding.tvContent.text = article.content

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btnSave.setOnClickListener {
            lifecycleScope.launch {
                getApp().settings!!.getUser().collect{
                    viewModel.saveNewsArticle(article, it!!)
                }
            }

            Snackbar.make(binding.root, R.string.fav_save_success, Snackbar.LENGTH_SHORT)
                .show()
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
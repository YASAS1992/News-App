package com.example.newsapp.activities

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.newsapp.R
import com.example.newsapp.data.Article
import com.example.newsapp.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewsBinding

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

        binding.tvDate.text = article.publishedAt
        binding.tvTitle.text = article.title
        binding.tvAuthor.text = "${getString(R.string.published_by)} ${article.author }}"
        binding.tvContent.text = article.content

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
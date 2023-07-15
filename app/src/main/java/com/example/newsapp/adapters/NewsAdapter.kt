package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.data.Article
import com.example.newsapp.databinding.NewsItemBinding
import java.util.ArrayList

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.NewViewHolder>() {

    private var data = ArrayList<Article>()

    fun setData(data:ArrayList<Article>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        return NewViewHolder(NewsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        var article = data[position]
        Glide.with(holder.itemView)
            .load(article.urlToImage)
            .into(holder.binding.ivNews)

        holder.binding.tvAuthor.text = article.author
        holder.binding.tvTitle.text = article.title
        holder.binding.tvDate.text = article.publishedAt
    }

    inner class NewViewHolder(var binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root)
}
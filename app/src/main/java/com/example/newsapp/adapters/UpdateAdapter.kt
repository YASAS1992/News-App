package com.example.newsapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.activities.NewsActivity
import com.example.newsapp.data.Article
import com.example.newsapp.databinding.UpdateItemBinding
import com.example.newsapp.utill.TextFormatter
import java.util.ArrayList

class UpdateAdapter(
    val context: Context
) : RecyclerView.Adapter<UpdateAdapter.UpdateViewHolder>(){

    private var data = ArrayList<Article>()

    fun setData(data:ArrayList<Article>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpdateViewHolder {
        return UpdateViewHolder(
            UpdateItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: UpdateViewHolder, position: Int) {
        var article = data[position]
        Glide.with(holder.itemView)
            .load(article.urlToImage)
            .into(holder.binding.ivNews)

        holder.binding.tvAuthor.text = article.author
        holder.binding.tvTitle.text = article.title
        holder.binding.tvDate.text = TextFormatter.customDateFormatter(article.publishedAt,"yyyy-MM-dd'T'HH:mm:ss'Z'","EEEE, d MMMM yyyy")
        holder.binding.tvContent.text = article.content

        holder.binding.tvReadMore.setOnClickListener {
            val i = Intent(context, NewsActivity::class.java)
            i.putExtra("article",article)
            context.startActivity(i)
        }
    }

    inner class UpdateViewHolder(var binding:UpdateItemBinding):RecyclerView.ViewHolder(binding.root)

}
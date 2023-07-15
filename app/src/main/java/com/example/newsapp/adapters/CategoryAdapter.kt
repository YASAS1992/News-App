package com.example.newsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.Category
import com.example.newsapp.databinding.CategoryItemBinding
import com.example.newsapp.viewmodel.HomeViewModel

class CategoryAdapter(
    private val context: Context,
    private val viewModel: HomeViewModel,
    var listener: (()->Unit)
):RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var data = ArrayList<Category>()

    inner class ViewHolder(var binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setData(data : ArrayList<Category>){
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var category = data[position]
        holder.binding.tvCategory.text = category.name
        if (viewModel.category == category.code){
            holder.binding.tvCategory.setBackgroundResource(R.drawable.category_select_bg)
            holder.binding.tvCategory.setTextColor(context.resources.getColor(R.color.white))
        }else{
            holder.binding.tvCategory.setBackgroundResource(R.drawable.category_deselect_bg)
            holder.binding.tvCategory.setTextColor(context.resources.getColor(R.color.black))
        }

        holder.itemView.setOnClickListener {
            viewModel.category = category.code
            notifyDataSetChanged()
        }
    }

}
package com.lazior.demonews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lazior.demonews.R
import com.lazior.demonews.model.News
import com.lazior.demonews.databinding.NewsItemBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.DataViewHolder>() {

    private var _data = mutableListOf<News>()

    fun setData(news: List<News>) {
        this._data = news.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(_data[position].url)
            .thumbnail(0.5f)
            .centerCrop()
            .error(R.mipmap.ic_launcher)
            .into(holder.binding.newsImg)
        holder.binding.newsTitle.text = _data[position].title
    }

    override fun getItemCount(): Int = _data.size

    class DataViewHolder(val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root) {
    }
}
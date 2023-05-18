package com.example.sampleapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampleapplication.R
import com.example.sampleapplication.mvvm.dataModel.DataModelItem

class RetrofitRecyclerAdapter(val data: ArrayList<DataModelItem>) : RecyclerView.Adapter<RetrofitRecyclerAdapter.ViewHolder>() {
    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val image:ImageView = view.findViewById(R.id.image)
        val author:TextView = view.findViewById(R.id.author)
        val url:TextView = view.findViewById(R.id.url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.retrofit_recycler_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.author.text = item.author
        holder.url.text = item.url
        Glide.with(holder.itemView).load(item.download_url).into(holder.image)
    }
}
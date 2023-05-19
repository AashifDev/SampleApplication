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

class RetrofitFragmentAdapter(val data: ArrayList<DataModelItem>): RecyclerView.Adapter<RetrofitFragmentAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.image)
        val author:TextView = itemView.findViewById(R.id.author)
        val url:TextView = itemView.findViewById(R.id.url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.retrofit_row_item, parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.author.text = item.author
        holder.url.text = item.url
        Glide.with(holder.itemView).load(item.download_url).into(holder.image)
    }
}
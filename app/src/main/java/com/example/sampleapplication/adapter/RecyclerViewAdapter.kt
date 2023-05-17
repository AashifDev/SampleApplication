package com.example.sampleapplication.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapplication.R
import com.example.sampleapplication.mainUi.fragment.FirebaseDbFragment
import com.example.sampleapplication.model.Note
import com.google.firebase.firestore.FirebaseFirestore

class RecyclerViewAdapter(val list: ArrayList<Note>, val listener:FirebaseDbFragment): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.description)
        val constraintLayout: ConstraintLayout = view.findViewById(R.id.constraintLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.title.text = item.title
        holder.description.text = item.description

        holder.constraintLayout.setOnClickListener {
            val bundle = Bundle().apply {
                putString("id",item.id)
            }
            holder.itemView.findNavController().navigate(R.id.action_firebaseDbFragment_to_addUpdateFragment,bundle)
        }



    }
}
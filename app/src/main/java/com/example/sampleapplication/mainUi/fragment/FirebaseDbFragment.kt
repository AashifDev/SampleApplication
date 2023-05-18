package com.example.sampleapplication.mainUi.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sampleapplication.R
import com.example.sampleapplication.adapter.RecyclerViewAdapter
import com.example.sampleapplication.databinding.FragmentFirebaseDbBinding
import com.example.sampleapplication.model.Note
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class FirebaseDbFragment : Fragment() {
    lateinit var binding: FragmentFirebaseDbBinding
    lateinit var adapter: RecyclerViewAdapter
    lateinit var arrList: ArrayList<Note>
    lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirebaseDbBinding.inflate(layoutInflater, container, false)
        binding.progressBar.visibility = View.VISIBLE
        binding.noDataFound.visibility = View.GONE
        db = FirebaseFirestore.getInstance()
        arrList = arrayListOf()
        adapter = RecyclerViewAdapter(arrList,this)
        setAdapter()
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    private fun setAdapter() {
        db.collection("note")
            .orderBy("title",Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener {
                binding.progressBar.visibility = View.GONE
                if (!it.isEmpty){
                    for (d:DocumentChange in it.documentChanges){
                        arrList.add(d.document.toObject(Note::class.java))
                    }
                    adapter.notifyDataSetChanged()
                }else{
                    binding.noDataFound.visibility = View.VISIBLE
                    Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show()

                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                binding.noDataFound.visibility = View.VISIBLE
                Toast.makeText(context, "Unable to load data", Toast.LENGTH_SHORT).show()
            }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.fabAddNote.setOnClickListener {
           findNavController().navigate(R.id.action_firebaseDbFragment_to_addFragment)
        }
    }


}
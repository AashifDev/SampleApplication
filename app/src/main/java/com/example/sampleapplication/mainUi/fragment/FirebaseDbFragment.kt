package com.example.sampleapplication.mainUi.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sampleapplication.R
import com.example.sampleapplication.adapter.RecyclerViewAdapter
import com.example.sampleapplication.databinding.FragmentFirebaseDbBinding
import com.example.sampleapplication.model.Note
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot


class FirebaseDbFragment : Fragment() {
    lateinit var binding: FragmentFirebaseDbBinding
    lateinit var adapter: RecyclerViewAdapter
    lateinit var list: ArrayList<Note>
    lateinit var db: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirebaseDbBinding.inflate(layoutInflater, container, false)
        db = FirebaseFirestore.getInstance()
        list = arrayListOf()
        adapter = RecyclerViewAdapter(list)
        setAdapter()
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    private fun setAdapter() {
        db.collection("notes")
            .addSnapshotListener(object :EventListener<QuerySnapshot>{
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null){
                        Log.e("tag", error.message.toString())
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!){
                        if (dc.type == DocumentChange.Type.ADDED){
                            list.add(dc.document.toObject(Note::class.java))
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.fabAddNote.setOnClickListener {
           findNavController().navigate(R.id.action_firebaseDbFragment_to_addUpdateFragment)
        }

    }
}
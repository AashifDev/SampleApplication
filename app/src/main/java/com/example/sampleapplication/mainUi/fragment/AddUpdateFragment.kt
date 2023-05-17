package com.example.sampleapplication.mainUi.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sampleapplication.R
import com.example.sampleapplication.databinding.FragmentAddUpdateBinding
import com.example.sampleapplication.databinding.FragmentFirebaseDbBinding
import com.example.sampleapplication.model.Note
import com.google.firebase.firestore.FirebaseFirestore


class AddUpdateFragment : Fragment() {

    lateinit var binding: FragmentAddUpdateBinding
    var title=""
    var description=""
    lateinit var db: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddUpdateBinding.inflate(layoutInflater, container, false)
        title = arguments?.getString("title").toString()
        description = arguments?.getString("description").toString()
        Log.d("title", title)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.fabSave.setOnClickListener {

            if (validDate()){
                val data = Note(
                    title,
                    description
                )

                db = FirebaseFirestore.getInstance()
                db.collection("notes")
                    .add(data)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_addUpdateFragment_to_firebaseDbFragment)
                    }
                    .addOnFailureListener { Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show() }

            }
        }
    }

    private fun validDate(): Boolean {
        title = binding.title.text.toString()
        description = binding.description.text.toString()
        if (TextUtils.isEmpty(title) && title==""){
            Toast.makeText(requireContext(), "Title empty", Toast.LENGTH_SHORT).show()
            return false
        }else if (TextUtils.isEmpty(description) && description ==""){
            Toast.makeText(requireContext(), "Description empty", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }



}
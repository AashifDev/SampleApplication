package com.example.sampleapplication.mainUi.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sampleapplication.R
import com.example.sampleapplication.databinding.FragmentAddBinding
import com.example.sampleapplication.model.Note
import com.google.firebase.firestore.FirebaseFirestore

class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    lateinit var data: ArrayList<Note>
    lateinit var db: FirebaseFirestore
    var title = ""
    var description = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        data = arrayListOf()
        db = FirebaseFirestore.getInstance()
        binding.fabSave.setOnClickListener {
            addToDb()
        }

        return binding.root
    }

    private fun addToDb() {
        val id = db.collection("note").document().id
        if (validDate()){
            binding.progressBar.visibility = View.VISIBLE
            val note = Note(id, title, description)
            db.collection("note")
                .document(id)
                .set(note)
                .addOnSuccessListener {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_addUpdateFragment_to_firebaseDbFragment)
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
        }
    }
    private fun validDate(): Boolean {
        title = binding.title.text.toString()
        description = binding.description.text.toString()
        if (TextUtils.isEmpty(title) && title==""){
            Toast.makeText(requireContext(), "Title empty", Toast.LENGTH_SHORT).show()
            return false
        }
        else if (TextUtils.isEmpty(description) && description ==""){
            Toast.makeText(requireContext(), "Description empty", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}
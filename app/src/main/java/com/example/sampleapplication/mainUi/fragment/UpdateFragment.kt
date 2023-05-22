package com.example.sampleapplication.mainUi.fragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sampleapplication.R
import com.example.sampleapplication.databinding.FragmentUpdateBinding
import com.example.sampleapplication.model.Note
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar

class UpdateFragment : Fragment() {

    lateinit var binding: FragmentUpdateBinding
    lateinit var data: ArrayList<Note>
    lateinit var db: FirebaseFirestore

    var currentId = ""
    var title = ""
    var description = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(layoutInflater, container, false)
        data = arrayListOf()
        db = FirebaseFirestore.getInstance()
        currentId = arguments?.getString("id").toString()
        title = binding.title.setText(arguments?.getString("title")).toString()
        description = binding.description.setText(arguments?.getString("description")).toString()

        binding.fabSave.setOnClickListener {
            if (currentId != ""){
                updateToDb()
                binding.progressBar.visibility = View.VISIBLE
            }
        }

        binding.fabDelete.setOnClickListener {
            if (currentId != ""){
                deleteFromDb()
                binding.fabDelete.isClickable = false
                binding.progressBar.visibility = View.VISIBLE
            }
        }

        clearBackStackOnBackPressed()
        return binding.root
    }

    private fun clearBackStackOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                requireActivity().intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
            }

        })
    }

    private fun deleteFromDb() {
        db.collection("note")
            .document(currentId)
            .delete()
            .addOnSuccessListener {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_firebaseDbFragment)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
    }

    private fun updateToDb() {

        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateTimeInstance()
        val currentDate = formatter.format(date)

        if (validDate()){
            binding.fabSave.isClickable = false
            val note = Note(currentId,title,description,currentDate)
            db.collection("note")
                .document(currentId)
                .set(note)
                .addOnSuccessListener {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_updateFragment_to_firebaseDbFragment)
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
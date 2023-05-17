package com.example.sampleapplication.mainUi.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sampleapplication.databinding.FragmentAddUpdateBinding
import com.example.sampleapplication.model.Note
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AddUpdateFragment : Fragment() {

    lateinit var binding: FragmentAddUpdateBinding
    lateinit var data: ArrayList<Note>
    var title=""
    var description=""
    //lateinit var db: FirebaseFirestore
    lateinit var db: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddUpdateBinding.inflate(layoutInflater, container, false)
        data = arrayListOf()
        binding.progressBar.visibility = View.GONE
   /*     binding.title.setText(arguments?.getString("title").toString())
        binding.description.setText(arguments?.getString("description").toString())
        id = arguments?.getString("id").toString()*/
        //db = FirebaseFirestore.getInstance()
        db = Firebase.database.reference

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.fabSave.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
      /*      if (validDate()){
                val data2 = Note(
                    title = title,
                    description = description
                )
                db.collection("notes")
                    .add(data2)
                    .addOnSuccessListener {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_addUpdateFragment_to_firebaseDbFragment)
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.GONE
                    }
            }*/

            if (validDate()){
                val key = db.child("note").push().key
                if (key == null) {
                    Log.w(TAG, "Couldn't get push key for posts")
                    return@setOnClickListener
                }

                val note = Note(id.toString(), title, description)

                val childUpdates = hashMapOf<String, Any>(
                    "/notes/$key" to note,
                )

                db.updateChildren(childUpdates)

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
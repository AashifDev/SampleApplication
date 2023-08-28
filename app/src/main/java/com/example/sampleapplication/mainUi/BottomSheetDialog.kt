package com.example.sampleapplication.mainUi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.sampleapplication.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_layout, container, false)

        /*val algoButton = view.findViewById<Button>(R.id.algo_button)
        val courseButton = view.findViewById<Button>(R.id.course_button)*/

        /*algoButton.setOnClickListener {
            Toast.makeText(activity, "Algorithm Shared", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        courseButton.setOnClickListener {
            Toast.makeText(activity, "Course Shared", Toast.LENGTH_SHORT).show()
            dismiss()
        }*/

        return view
    }
}
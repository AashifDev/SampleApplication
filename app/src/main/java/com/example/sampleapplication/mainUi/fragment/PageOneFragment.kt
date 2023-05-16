package com.example.sampleapplication.mainUi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sampleapplication.R
import com.example.sampleapplication.databinding.FragmentPageOneBinding

class PageOneFragment : Fragment() {
    lateinit var binding: FragmentPageOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentPageOneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}
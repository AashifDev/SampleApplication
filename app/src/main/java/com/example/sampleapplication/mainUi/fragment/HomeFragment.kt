package com.example.sampleapplication.mainUi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.sampleapplication.R
import com.example.sampleapplication.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        binding.tabLayout.setOnClickListener { findNavController().navigate(R.id.tabLayoutFragment) }
        binding.firebaseDb.setOnClickListener { findNavController().navigate(R.id.firebaseDbFragment) }
        binding.googleMap.setOnClickListener { findNavController().navigate(R.id.googleMapFragment)}
        binding.retrofit.setOnClickListener { findNavController().navigate(R.id.retrofitFragment) }
    }

}
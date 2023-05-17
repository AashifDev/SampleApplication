package com.example.sampleapplication.mainUi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sampleapplication.R
import com.example.sampleapplication.databinding.FragmentPageOneBinding
import kotlin.random.Random

class PageOneFragment : Fragment() {
    lateinit var binding: FragmentPageOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPageOneBinding.inflate(layoutInflater, container, false)
        binding.roll.setOnClickListener {
            rollDice()
        }
        binding.diceImage.setImageResource(R.drawable.dice_1)
        return binding.root
    }

    private fun rollDice() {
        val diceImage = when(Random.nextInt(6)){
            1-> R.drawable.dice_1
            2-> R.drawable.dice_2
            3-> R.drawable.dice_3
            4-> R.drawable.dice_4
            5-> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        binding.diceImage.setImageResource(diceImage)

    }


}
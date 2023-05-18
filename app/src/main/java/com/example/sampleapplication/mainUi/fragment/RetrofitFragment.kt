package com.example.sampleapplication.mainUi.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.sampleapplication.adapter.RetrofitRecyclerAdapter
import com.example.sampleapplication.databinding.FragmentRetrofitBinding
import com.example.sampleapplication.mvvm.ViewModel
import com.example.sampleapplication.mvvm.dataModel.DataModelItem

class RetrofitFragment : Fragment() {
    lateinit var binding: FragmentRetrofitBinding
    lateinit var arrList: ArrayList<DataModelItem>
    lateinit var adapter: RetrofitRecyclerAdapter
    val viewModel by viewModels<ViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRetrofitBinding.inflate(layoutInflater, container, false)
        arrList = arrayListOf()

        getData()
        return binding.root
    }

    private fun getData() {
        viewModel.getData()
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            if (it != null){
                arrList = it
                adapter = RetrofitRecyclerAdapter(arrList)
                Log.d("tag", arrList.toString())
                binding.recyclerView.adapter = adapter
            }else{
                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
            }
        })
    }


}
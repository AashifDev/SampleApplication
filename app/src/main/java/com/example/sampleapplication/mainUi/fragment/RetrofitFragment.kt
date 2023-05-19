package com.example.sampleapplication.mainUi.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.sampleapplication.adapter.RetrofitFragmentAdapter
import com.example.sampleapplication.databinding.FragmentRetrofitBinding
import com.example.sampleapplication.mvvm.ViewModel
import com.example.sampleapplication.mvvm.dataModel.DataModelItem

class RetrofitFragment : Fragment() {
    lateinit var binding: FragmentRetrofitBinding
    lateinit var arrayList: ArrayList<DataModelItem>
    lateinit var adapter: RetrofitFragmentAdapter
    val viewModel by viewModels<ViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRetrofitBinding.inflate(layoutInflater, container, false)
        binding.progressBar.visibility = View.VISIBLE

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData() {
        viewModel.getData()
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            if (it != null){
                arrayList = ArrayList()
                adapter = RetrofitFragmentAdapter(arrayList)
                arrayList.addAll(it)
                binding.recyclerView.adapter = adapter
                binding.progressBar.visibility = View.GONE
                Log.d("tag",it.toString())
            }
        })
    }


}
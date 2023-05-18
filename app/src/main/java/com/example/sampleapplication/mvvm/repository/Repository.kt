package com.example.sampleapplication.mvvm.repository

import com.example.sampleapplication.mvvm.apiInterface.ApiInterface
import com.example.sampleapplication.mvvm.dataModel.DataModel
import com.example.sampleapplication.mvvm.retrofitHelper.Retrofit

class Repository {
    suspend fun repository(): DataModel {
        return Retrofit().retrofit().create(ApiInterface::class.java).getData()
    }
}
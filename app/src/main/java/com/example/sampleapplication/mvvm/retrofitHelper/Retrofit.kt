package com.example.sampleapplication.mvvm.retrofitHelper

import com.example.sampleapplication.mvvm.apiInterface.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    fun retrofitHelper(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl("https://picsum.photos/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}
package com.example.sampleapplication.mvvm.apiInterface

import com.example.sampleapplication.mvvm.dataModel.DataModel

import retrofit2.http.GET


interface ApiInterface {
    @GET()
    suspend fun getData(): DataModel
}
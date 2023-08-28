package com.example.sampleapplication.mvvm.apiInterface

import com.example.sampleapplication.model.SearchAddressResponse
import com.example.sampleapplication.mvvm.dataModel.DataModel
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    @GET("list ")
    suspend fun getData(): DataModel

    @GET("https://maps.googleapis.com/maps/api/place/autocomplete/json")
    fun getLocationSuggestion(
        @Query("key") key: String? = null,
        @Query("input") input: String
    ): SearchAddressResponse
}
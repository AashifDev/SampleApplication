package com.example.sampleapplication.mvvm.retrofitHelper

import com.example.sampleapplication.mvvm.apiInterface.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
 /*   var service: ApiInterface?= null
    val client: ApiInterface?
        get() {
            if (service != null) {
                service = Retrofit.Builder()
                    .baseUrl("https://picsum.photos/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiInterface::class.java)
            }
            return service
        }*/
    fun retrofit(): Retrofit{
        return Retrofit.Builder()
         .baseUrl("https://picsum.photos/v2/")
         .addConverterFactory(GsonConverterFactory.create())
         .build()
    }
}
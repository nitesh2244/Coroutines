package com.example.coroutines.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retro {
    companion object {
        var retrofitService: ApiInterface? = null
        fun getInstance(): ApiInterface {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiInterface::class.java)
            }
            return retrofitService!!
        }

    }
}
package com.example.coroutines.network

import com.example.coroutines.Model.ApiDataItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    suspend fun getPost(): List<ApiDataItem>

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
package com.example.coroutines.network

import com.example.coroutines.Model.ApiDataItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    suspend fun getPost(): List<ApiDataItem>

}
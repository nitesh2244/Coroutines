package com.example.coroutines.repository

import com.example.coroutines.network.ApiInterface

class UserRepo constructor(private val apiInterface: ApiInterface) {
    suspend fun getData() = apiInterface.getPost()
}
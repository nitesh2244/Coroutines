package com.example.coroutines.repository

import com.example.coroutines.Model.ApiDataItem
import com.example.coroutines.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepo constructor(private val apiInterface: ApiInterface) {
    fun getFlow(): Flow<List<ApiDataItem>> = flow {
        while (true) {
            emit(apiInterface.getPost())
        }
    }.flowOn(Dispatchers.IO)
    // suspend fun getData() = apiInterface.getPost()
}
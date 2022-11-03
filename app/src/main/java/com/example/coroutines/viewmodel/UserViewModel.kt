package com.example.coroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.Model.ApiDataItem
import com.example.coroutines.repository.UserRepo
import kotlinx.coroutines.*

class UserViewModel constructor(private val userRepo: UserRepo) : ViewModel() {

    val userList: MutableLiveData<List<ApiDataItem>> by lazy { MutableLiveData<List<ApiDataItem>>() }

    fun getUser() {
        viewModelScope.launch {
            val response = userRepo.getData()
            userList.postValue(response)
        }
    }
}
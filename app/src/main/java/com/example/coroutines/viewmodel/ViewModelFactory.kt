package com.example.coroutines.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coroutines.repository.UserRepo
import java.lang.IllegalArgumentException

class ViewModelFactory constructor(private val repo: UserRepo) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            UserViewModel(this.repo) as T
        } else {
            throw IllegalArgumentException("not found")
        }
    }
}
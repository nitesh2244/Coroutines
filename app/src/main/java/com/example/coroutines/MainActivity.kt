package com.example.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.coroutines.databinding.ActivityMainBinding
import com.example.coroutines.network.ApiInterface
import com.example.coroutines.repository.UserRepo
import com.example.coroutines.viewmodel.UserViewModel
import com.example.coroutines.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val apiInterface = ApiInterface.getInstance()
        val userRepo = UserRepo(apiInterface)

        userViewModel =
            ViewModelProvider(this, ViewModelFactory(userRepo))[UserViewModel::class.java]

        binding.btn.setOnClickListener {
            userViewModel.getUser()
        }

        userViewModel.userList.observe(this) {
            binding.tv.text = it[0].title
        }

    }
}


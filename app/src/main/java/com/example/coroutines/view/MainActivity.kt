package com.example.coroutines.view

import android.app.PendingIntent.getActivity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.Model.ApiDataItem
import com.example.coroutines.R
import com.example.coroutines.adapter.Adapter
import com.example.coroutines.databinding.ActivityMainBinding
import com.example.coroutines.network.Retro
import com.example.coroutines.repository.UserRepo
import com.example.coroutines.viewmodel.UserViewModel
import com.example.coroutines.viewmodel.ViewModelFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: Adapter
    lateinit var flow: Flow<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // setupClicks()
        setupFlow()

        val retro = Retro.getInstance()
        val userRepo = UserRepo(retro)


        userViewModel =
            ViewModelProvider(this, ViewModelFactory(userRepo))[UserViewModel::class.java]

        binding.btn.setOnClickListener {
            userViewModel.getUser()
        }

        userViewModel.userList.observe(this) {
            Log.d("daadadad", "onCreate: " + it)
            initRecycler(it)

        }
    }

    private fun setupFlow() {
        flow = flow {
            Log.d(TAG, "Start flow")
            (0..10).forEach {
                delay(2000)
                emit(it)
            }
        }.flowOn(Dispatchers.Default)
    }

//    private fun setupClicks() {
//        binding.startFlow.setOnClickListener {
//            CoroutineScope(Dispatchers.Main).launch {
//                flow.collect {
//                    Log.d(TAG, it.toString())
//                }
//            }
//        }
//    }

    private fun initRecycler(list: List<ApiDataItem>) {
        binding.rv.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,false
            )
        )
        binding.rv.setHasFixedSize(true)
        binding.rv.setItemAnimator(DefaultItemAnimator())
        adapter = Adapter(this, list)
        binding.rv.setAdapter(adapter)
    }
}


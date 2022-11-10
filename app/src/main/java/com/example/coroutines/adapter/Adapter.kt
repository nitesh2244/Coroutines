package com.example.coroutines.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines.Model.ApiDataItem
import com.example.coroutines.databinding.ItemBinding

class Adapter(val context: Context, var list: List<ApiDataItem>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private lateinit var itemBinding: ItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        itemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            itemBinding.item.text = list[0].body

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(private val data: ItemBinding) : RecyclerView.ViewHolder(data.root) {

//        fun bind(list: List<ApiDataItem>){
//          data.item.text = list[0].title
//        }

    }
}
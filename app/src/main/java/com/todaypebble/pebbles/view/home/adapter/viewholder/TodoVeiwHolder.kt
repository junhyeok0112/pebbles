package com.todaypebble.pebbles.view.home.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.Todo
import com.todaypebble.pebbles.databinding.ItemTodoHomeBinding

class TodoVeiwHolder(private var binding : ItemTodoHomeBinding) : RecyclerView.ViewHolder(binding.root) {

    //Todo와 ViewHolder 연결하기
    fun bind(item : Todo) = with(binding){
        this.todo = item            //binding의 variable과 연결
    }

    fun getBinding() = binding
}

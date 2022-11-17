package com.todaypebble.pebbles.view.manage.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.Todo
import com.todaypebble.pebbles.data.remote.dto.manage.MyStone
import com.todaypebble.pebbles.data.remote.model.Habit
import com.todaypebble.pebbles.databinding.ItemHabitHomeBinding
import com.todaypebble.pebbles.databinding.ItemStoneManageBinding
import com.todaypebble.pebbles.view.home.adapter.HabitRVAdapter
import com.todaypebble.pebbles.view.home.adapter.TodoRVAdapter
import com.todaypebble.pebbles.view.manage.adapter.MyStoneRVAdapter

class MyStoneViewHolder(private val binding : ItemStoneManageBinding) : RecyclerView.ViewHolder(binding.root) {
    //DataBinding 연결하기 / with란?
    fun bind(item: MyStone , listener: MyStoneRVAdapter.StoneClickListener) = with(binding){
        this.myStone = item
        binding.root.setOnClickListener {
            listener.onStoneClickListener(item.id)
        }
    }

    fun getBinding() : ItemStoneManageBinding = binding
}
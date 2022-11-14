package com.todaypebble.pebbles.view.manage.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.manage.Habit
import com.todaypebble.pebbles.databinding.ItemResultBinding
import com.todaypebble.pebbles.view.manage.adapter.StoneAddTodoResultAdapter

class StoneAddResultViewHolder(private val binding :ItemResultBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(item :Habit) {
        binding.itemSandAddTitleTv.text = item.name
        //Todo 어댑터
        val adapter = StoneAddTodoResultAdapter(item.todos)
        binding.itemSandAddTodoRv.adapter = adapter
    }

}
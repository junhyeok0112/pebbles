package com.todaypebble.pebbles.view.manage.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.manage.Todo
import com.todaypebble.pebbles.databinding.ItemResultBinding
import com.todaypebble.pebbles.databinding.ItemResultTodoBinding
import com.todaypebble.pebbles.databinding.ItemSandTodoBinding

class StoneAddTodoResultViewHolder(private val binding : ItemResultTodoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item : Todo){
        binding.itemResultTodoTv.text = item.name
    }
}
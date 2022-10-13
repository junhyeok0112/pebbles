package com.example.pebbles.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pebbles.data.remote.dto.Todo
import com.example.pebbles.databinding.ItemTodoHomeBinding
import com.example.pebbles.view.home.adapter.viewholder.TodoVeiwHolder

class TodoRVAdapter(private var todos : ArrayList<Todo>) : RecyclerView.Adapter<TodoVeiwHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoVeiwHolder {
        val binding = ItemTodoHomeBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return TodoVeiwHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoVeiwHolder, position: Int) {
        todos.get(position).let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = todos.size
}
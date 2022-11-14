package com.todaypebble.pebbles.view.manage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.manage.Habit
import com.todaypebble.pebbles.data.remote.dto.manage.Todo
import com.todaypebble.pebbles.databinding.ItemResultBinding
import com.todaypebble.pebbles.databinding.ItemResultTodoBinding
import com.todaypebble.pebbles.view.manage.adapter.viewholder.StoneAddResultViewHolder
import com.todaypebble.pebbles.view.manage.adapter.viewholder.StoneAddTodoResultViewHolder

class StoneAddTodoResultAdapter(private var todos: ArrayList<Todo>) : RecyclerView.Adapter<StoneAddTodoResultViewHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoneAddTodoResultViewHolder {
        val binding = ItemResultTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoneAddTodoResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoneAddTodoResultViewHolder, position: Int) {
        todos.get(position).let{
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = todos.size
}
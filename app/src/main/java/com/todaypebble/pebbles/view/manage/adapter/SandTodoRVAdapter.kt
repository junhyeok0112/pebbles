package com.todaypebble.pebbles.view.manage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.manage.Habit
import com.todaypebble.pebbles.data.remote.dto.manage.Todo
import com.todaypebble.pebbles.databinding.ItemSandAddBinding
import com.todaypebble.pebbles.databinding.ItemSandTodoBinding
import com.todaypebble.pebbles.view.manage.adapter.viewholder.SandAddViewHolder
import com.todaypebble.pebbles.view.manage.adapter.viewholder.SandTodoViewHolder

class SandTodoRVAdapter(private var todos: ArrayList<Todo> , private val listener: SandAddRVAdapter.clickListener , private val habitPosition : Int) : RecyclerView.Adapter<SandTodoViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SandTodoViewHolder {
        val binding = ItemSandTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SandTodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SandTodoViewHolder, position: Int) {
        todos.get(position)?.let {      //해당값이 null이 아니면
            //Bind 하면서 해당 listener도 넘겨줌
            holder.bind(it , listener , habitPosition)
        }
    }

    override fun getItemCount(): Int = todos.size

}
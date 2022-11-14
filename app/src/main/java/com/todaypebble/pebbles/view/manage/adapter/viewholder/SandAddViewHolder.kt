package com.todaypebble.pebbles.view.manage.adapter.viewholder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.manage.Habit
import com.todaypebble.pebbles.data.remote.dto.manage.Todo
import com.todaypebble.pebbles.databinding.ItemHabitAddBinding
import com.todaypebble.pebbles.databinding.ItemSandAddBinding
import com.todaypebble.pebbles.view.manage.adapter.SandAddRVAdapter
import com.todaypebble.pebbles.view.manage.adapter.SandTodoRVAdapter

class SandAddViewHolder(private val binding : ItemSandAddBinding ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item : Habit ,listener: SandAddRVAdapter.clickListener , habitPosition :Int) = with(binding){
        binding.itemSandAddTitleTv.text = item.name

        //TodoList 만들어댑터
        var adapter = SandTodoRVAdapter(item.todos , listener , habitPosition)
        binding.itemSandAddTodoRv.adapter = adapter
        Log.d("SandADDViewHolder" , "${item.todos}")
        //추가 누르면 할 일 목록 추가하기 -> 리스너
        binding.itemSandAddTodoBtn.setOnClickListener {
            listener.addClickTodo(adapterPosition)
            adapter.notifyItemInserted(item.todos.size)
        }
    }
}
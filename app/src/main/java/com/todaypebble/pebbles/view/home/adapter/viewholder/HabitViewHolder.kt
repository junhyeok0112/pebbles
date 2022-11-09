package com.todaypebble.pebbles.view.home.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.Todo
import com.todaypebble.pebbles.data.remote.model.Habit

import com.todaypebble.pebbles.databinding.ItemHabitHomeBinding
import com.todaypebble.pebbles.view.home.adapter.HabitRVAdapter
import com.todaypebble.pebbles.view.home.adapter.TodoRVAdapter

class HabitViewHolder(private val binding : ItemHabitHomeBinding) : RecyclerView.ViewHolder(binding.root) {

    //DataBinding 연결하기 / with란?
   fun bind(item: Habit , listener: HabitRVAdapter.TodoButtonListener) = with(binding){
       this.habit = item
       //Adapter 만들어서 중복 리싸이클러뷰
        var adapter = TodoRVAdapter(ArrayList<Todo>(), item.id)
        if(item.todos.size != 0){
            adapter =  TodoRVAdapter(item.todos as ArrayList<Todo> , item.id) //해당 해빗이 가져온 todo 리스트 추가 , 해당 Habit의 ID 넘겨줌
        }
        adapter.setListener(listener)
        binding.itemHomeRecyclerRv.adapter = adapter
   }

    fun getBinding() : ItemHabitHomeBinding = binding

}
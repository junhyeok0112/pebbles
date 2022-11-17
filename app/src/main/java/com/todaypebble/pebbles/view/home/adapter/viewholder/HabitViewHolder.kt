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
        if(item.todos.isNotEmpty()){
            //Todo 가 1개 일때 ArrayList로 변경하면서 에러 ->1개일 때랑 구분해야하나 ? 구분해서 작성
            if(item.todos.size == 1){
                val temp = ArrayList<Todo>()
                temp.add(item.todos[0])
                adapter =  TodoRVAdapter(temp, item.id) //해당 해빗이 가져온 todo 리스트 추가 , 해당 Habit의 ID 넘겨줌
            } else{
                adapter =  TodoRVAdapter(item.todos as ArrayList<Todo>, item.id) //해당 해빗이 가져온 todo 리스트 추가 , 해당 Habit의 ID 넘겨줌
            }

        }
        adapter.setListener(listener)
        binding.itemHomeRecyclerRv.adapter = adapter
   }

    fun getBinding() : ItemHabitHomeBinding = binding

}
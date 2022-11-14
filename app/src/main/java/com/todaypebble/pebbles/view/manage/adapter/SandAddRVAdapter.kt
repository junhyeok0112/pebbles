package com.todaypebble.pebbles.view.manage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.manage.Habit
import com.todaypebble.pebbles.databinding.ItemHabitAddBinding
import com.todaypebble.pebbles.databinding.ItemSandAddBinding
import com.todaypebble.pebbles.view.manage.adapter.viewholder.HabitAddViewHolder
import com.todaypebble.pebbles.view.manage.adapter.viewholder.SandAddViewHolder

class SandAddRVAdapter(private var habits: MutableLiveData<ArrayList<Habit>>) : RecyclerView.Adapter<SandAddViewHolder>() {

    private lateinit var listener: SandAddRVAdapter.clickListener

    fun setListener(listener: SandAddRVAdapter.clickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SandAddViewHolder {
        val binding = ItemSandAddBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SandAddViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SandAddViewHolder, position: Int) {
        //let 함수를 쓰면 객체의 상태를 변경할 수 있다.
        //또한 non-null이므로 null체크에도 용이
        habits.value?.get(position)?.let {      //해당값이 null이 아니면
            //Bind 하면서 해당 listener도 넘겨줌
            holder.bind(it , listener ,position)
        }
    }

    override fun getItemCount(): Int {
        if (habits.value != null) {
            return habits.value?.size!!
        } else {
            return 0
        }
    }

    interface clickListener {
        fun addClickTodo(position: Int)
        fun writeTodo(position :Int , habitPosition: Int , name : String)
        fun deleteTodo(position: Int , habitPosition: Int)
    }
}
package com.todaypebble.pebbles.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.Todo
import com.todaypebble.pebbles.data.remote.model.Habit
import com.todaypebble.pebbles.databinding.ItemHabitHomeBinding
import com.todaypebble.pebbles.view.home.adapter.viewholder.HabitViewHolder

class HabitRVAdapter(private var habbits : LiveData<List<Habit>>): RecyclerView.Adapter<HabitViewHolder>()  {

    private lateinit var listener: TodoButtonListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ItemHabitHomeBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        //let 함수를 쓰면 객체의 상태를 변경할 수 있다.
        //또한 non-null이므로 null체크에도 용이
        habbits.value?.get(position)?.let {      //해당값이 null이 아니면
            //Bind 하면서 해당 listener도 넘겨줌
            holder.bind(it,listener)
        }

        holder.getBinding().itemHabbitDetailIv.setOnClickListener { listener.onDetailClick(habbits.value!!.get(position)) }

    }

    override fun getItemCount(): Int{
        if(habbits.value != null){
            return habbits.value?.size!!
        } else{
            return 0
        }
    }

    fun setListener(listener: TodoButtonListener){
        this.listener = listener
    }

    override fun getItemId(position: Int): Long {
        return habbits.value!!.get(position).id.toLong()
    }

    interface TodoButtonListener {
        fun onSandClick(item : Todo , habit_id : Int)
        fun onDetailClick(item: Habit)
    }
}
package com.todaypebble.pebbles.view.manage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.manage.Habit
import com.todaypebble.pebbles.data.remote.dto.manage.Weeks
import com.todaypebble.pebbles.databinding.ItemHabitAddBinding
import com.todaypebble.pebbles.databinding.ItemHabitHomeBinding
import com.todaypebble.pebbles.view.home.adapter.HabitRVAdapter
import com.todaypebble.pebbles.view.home.adapter.viewholder.HabitViewHolder
import com.todaypebble.pebbles.view.manage.adapter.viewholder.HabitAddViewHolder

class HabitAddRVAdapter (private var habits : MutableLiveData<ArrayList<Habit>>): RecyclerView.Adapter<HabitAddViewHolder>() {

    private lateinit var listener: HabitAddRVAdapter.clickListener

    fun setListener(listener: clickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitAddViewHolder {
        val binding = ItemHabitAddBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return HabitAddViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitAddViewHolder, position: Int) {
        //let 함수를 쓰면 객체의 상태를 변경할 수 있다.
        //또한 non-null이므로 null체크에도 용이
        habits.value?.get(position)?.let {      //해당값이 null이 아니면
            //Bind 하면서 해당 listener도 넘겨줌
            holder.bind(it ,listener)
        }

//        holder.getBinding().itemHabbitDetailIv.setOnClickListener { listener.onDetailClick(habbits.value!!.get(position)) }

    }

    override fun getItemCount(): Int {
        if(habits.value != null){
            return habits.value?.size!!
        } else{
            return 0
        }
    }

    interface clickListener {
        fun onWeekClick(position : Int ,tempWeeks: Weeks)
        fun onStartDayClick(position : Int)
        fun onEndDayClick(position : Int)
        fun deleteHabit(position : Int)
    }

}
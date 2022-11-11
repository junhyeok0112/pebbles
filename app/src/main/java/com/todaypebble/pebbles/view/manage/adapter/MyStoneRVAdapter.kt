package com.todaypebble.pebbles.view.manage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.manage.MyStone
import com.todaypebble.pebbles.data.remote.model.Habit
import com.todaypebble.pebbles.databinding.ItemHabitHomeBinding
import com.todaypebble.pebbles.databinding.ItemStoneManageBinding
import com.todaypebble.pebbles.view.home.adapter.viewholder.HabitViewHolder
import com.todaypebble.pebbles.view.manage.adapter.viewholder.MyStoneViewHolder

class MyStoneRVAdapter (private var stones : ArrayList<MyStone>): RecyclerView.Adapter<MyStoneViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyStoneViewHolder {
        val binding = ItemStoneManageBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return MyStoneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyStoneViewHolder, position: Int) {
        //let 함수를 쓰면 객체의 상태를 변경할 수 있다.
        //또한 non-null이므로 null체크에도 용이
        stones.get(position)?.let {      //해당값이 null이 아니면
            //Bind 하면서 해당 listener도 넘겨줌
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = stones.size
}
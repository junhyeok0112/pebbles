package com.todaypebble.pebbles.view.manage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.manage.DetailMyStoneHabitRes
import com.todaypebble.pebbles.databinding.ItemManageDetailBinding
import com.todaypebble.pebbles.databinding.ItemStoneManageBinding
import com.todaypebble.pebbles.view.manage.adapter.viewholder.DetailMyStoneViewHolder
import com.todaypebble.pebbles.view.manage.adapter.viewholder.MyStoneViewHolder

class DetailMyStoneRVAdapter(private val Habits : ArrayList<DetailMyStoneHabitRes>) : RecyclerView.Adapter<DetailMyStoneViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailMyStoneViewHolder {
        val binding = ItemManageDetailBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return DetailMyStoneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailMyStoneViewHolder, position: Int) {
        Habits.get(position).let {      //해당값이 null이 아니면
            //Bind 하면서 해당 listener도 넘겨줌
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = Habits.size
}
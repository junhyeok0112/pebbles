package com.todaypebble.pebbles.view.manage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.manage.DetailMyStoneTodoRes
import com.todaypebble.pebbles.databinding.ItemManageDetailBinding
import com.todaypebble.pebbles.databinding.ItemManageDetailSandBinding
import com.todaypebble.pebbles.view.manage.adapter.viewholder.DetailMyStoneSandViewHolder
import com.todaypebble.pebbles.view.manage.adapter.viewholder.DetailMyStoneViewHolder

class DetailMyStoneSandRVAdapter(private val todos : ArrayList<DetailMyStoneTodoRes>) : RecyclerView.Adapter<DetailMyStoneSandViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailMyStoneSandViewHolder {
        val binding = ItemManageDetailSandBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return DetailMyStoneSandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailMyStoneSandViewHolder, position: Int) {
        todos.get(position).let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = todos.size
}

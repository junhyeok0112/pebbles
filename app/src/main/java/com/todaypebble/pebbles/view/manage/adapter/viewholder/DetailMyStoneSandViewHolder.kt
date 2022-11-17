package com.todaypebble.pebbles.view.manage.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.manage.DetailMyStoneTodoRes
import com.todaypebble.pebbles.databinding.ItemManageDetailSandBinding

class DetailMyStoneSandViewHolder(private val binding : ItemManageDetailSandBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : DetailMyStoneTodoRes) {
        binding.todo = item
    }
}
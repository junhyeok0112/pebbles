package com.todaypebble.pebbles.view.manage.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.manage.DetailMyStoneHabitRes
import com.todaypebble.pebbles.databinding.ItemManageDetailBinding
import com.todaypebble.pebbles.view.manage.adapter.DetailMyStoneSandRVAdapter

class DetailMyStoneViewHolder(private val binding : ItemManageDetailBinding) : RecyclerView.ViewHolder(binding.root) {

    //바인딩 연결하기
    fun bind(item : DetailMyStoneHabitRes){
        binding.habit = item
        //요일 셋팅
        var week = ""
        if(item.weeks.mon) week +="월,"
        if(item.weeks.tue) week +="화,"
        if(item.weeks.wed) week +="수,"
        if(item.weeks.thu) week +="목,"
        if(item.weeks.fri) week +="금,"
        if(item.weeks.sat) week +="토,"
        if(item.weeks.sat) week +="일,"
        week = week.substring(0,week.length-1)  //마지막 점 제거
        binding.itemManageDetailWeekTv.text = week
        //Todo 어댑터 셋팅
        val adapter = DetailMyStoneSandRVAdapter(item.getRockManageDetailTodoResList)
        binding.itemManageDetailSandRv.adapter = adapter
    }
}
package com.todaypebble.pebbles.view.manage.adapter.viewholder

import android.app.DatePickerDialog
import android.util.Log
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.data.remote.dto.manage.Habit
import com.todaypebble.pebbles.data.remote.dto.manage.MyStone
import com.todaypebble.pebbles.data.remote.dto.manage.Weeks
import com.todaypebble.pebbles.databinding.ItemHabitAddBinding
import com.todaypebble.pebbles.databinding.ItemStoneManageBinding
import com.todaypebble.pebbles.view.manage.adapter.HabitAddRVAdapter
import java.text.SimpleDateFormat
import java.util.*

//Binding 되는지 , DatePicker 출력
class HabitAddViewHolder(private val binding : ItemHabitAddBinding) : RecyclerView.ViewHolder(binding.root) {

    val tempWeek = Weeks(false,false,false,false,false,false,false)
    var isTitleFill = false
    var isStartDayFill = true
    var isEndDayFill = true
    var isWeekFill = false

    fun bind(item: Habit ,listener: HabitAddRVAdapter.clickListener) = with(binding){
        this.habit = item
        initListener(listener , item)
        if(item.weeks.mon){
            binding.itemHabitMonSelected.visibility = View.VISIBLE
            binding.itemHabitMonUnselected.visibility = View.GONE
        } else{
            binding.itemHabitMonSelected.visibility = View.GONE
            binding.itemHabitMonUnselected.visibility = View.VISIBLE
        }

        if(item.weeks.tue){
            binding.itemHabitTueSelected.visibility = View.VISIBLE
            binding.itemHabitTueUnselected.visibility = View.GONE
        } else{
            binding.itemHabitTueSelected.visibility = View.GONE
            binding.itemHabitTueUnselected.visibility = View.VISIBLE
        }

        if(item.weeks.wed){
            binding.itemHabitWedSelected.visibility = View.VISIBLE
            binding.itemHabitWedUnselected.visibility = View.GONE
        } else{
            binding.itemHabitWedSelected.visibility = View.GONE
            binding.itemHabitWedUnselected.visibility = View.VISIBLE
        }

        if(item.weeks.thu){
            binding.itemHabitThuSelected.visibility = View.VISIBLE
            binding.itemHabitThuUnselected.visibility = View.GONE
        } else{
            binding.itemHabitThuSelected.visibility = View.GONE
            binding.itemHabitThuUnselected.visibility = View.VISIBLE
        }

        if(item.weeks.fri){
            binding.itemHabitFriSelected.visibility = View.VISIBLE
            binding.itemHabitFriUnselected.visibility = View.GONE
        } else{
            binding.itemHabitFriSelected.visibility = View.GONE
            binding.itemHabitFriUnselected.visibility = View.VISIBLE
        }

        if(item.weeks.sat){
            binding.itemHabitSatSelected.visibility = View.VISIBLE
            binding.itemHabitSatUnselected.visibility = View.GONE
        } else{
            binding.itemHabitSatSelected.visibility = View.GONE
            binding.itemHabitSatUnselected.visibility = View.VISIBLE
        }

        if(item.weeks.sun){
            binding.itemHabitSunSelected.visibility = View.VISIBLE
            binding.itemHabitSunUnselected.visibility = View.GONE
        } else{
            binding.itemHabitSunSelected.visibility = View.GONE
            binding.itemHabitSunUnselected.visibility = View.VISIBLE
        }
    }

    fun initListener(listener: HabitAddRVAdapter.clickListener , item :Habit){
        binding.itemHabitMonUnselected.setOnClickListener {
            binding.itemHabitMonUnselected.visibility = View.GONE
            binding.itemHabitMonSelected.visibility = View.VISIBLE
            tempWeek.mon = true
            listener.onWeekClick(adapterPosition ,tempWeek)
        }

        binding.itemHabitMonSelected.setOnClickListener {
            binding.itemHabitMonUnselected.visibility = View.VISIBLE
            binding.itemHabitMonSelected.visibility = View.GONE
            tempWeek.mon = false
            listener.onWeekClick(adapterPosition ,tempWeek)
        }

        binding.itemHabitTueUnselected.setOnClickListener {
            binding.itemHabitTueUnselected.visibility = View.GONE
            binding.itemHabitTueSelected.visibility = View.VISIBLE
            tempWeek.tue = true
            listener.onWeekClick(adapterPosition ,tempWeek)
        }

        binding.itemHabitTueSelected.setOnClickListener {
            binding.itemHabitTueUnselected.visibility = View.VISIBLE
            binding.itemHabitTueSelected.visibility = View.GONE
            tempWeek.tue = false
            listener.onWeekClick(adapterPosition ,tempWeek)
        }

        binding.itemHabitWedUnselected.setOnClickListener {
            binding.itemHabitWedUnselected.visibility = View.GONE
            binding.itemHabitWedSelected.visibility = View.VISIBLE
            tempWeek.wed = true
            listener.onWeekClick(adapterPosition ,tempWeek)
        }

        binding.itemHabitWedSelected.setOnClickListener {
            binding.itemHabitWedUnselected.visibility = View.VISIBLE
            binding.itemHabitWedSelected.visibility = View.GONE
            tempWeek.wed = false
            listener.onWeekClick(adapterPosition ,tempWeek)
        }

        binding.itemHabitThuUnselected.setOnClickListener {
            binding.itemHabitThuUnselected.visibility = View.GONE
            binding.itemHabitThuSelected.visibility = View.VISIBLE
            tempWeek.thu = true
            listener.onWeekClick(adapterPosition ,tempWeek)
        }

        binding.itemHabitThuSelected.setOnClickListener {
            binding.itemHabitThuUnselected.visibility = View.VISIBLE
            binding.itemHabitThuSelected.visibility = View.GONE
            tempWeek.thu = false
            listener.onWeekClick(adapterPosition ,tempWeek)
        }

        binding.itemHabitFriUnselected.setOnClickListener {
            binding.itemHabitFriUnselected.visibility = View.GONE
            binding.itemHabitFriSelected.visibility = View.VISIBLE
            tempWeek.fri = true
            listener.onWeekClick(adapterPosition ,tempWeek)
        }

        binding.itemHabitFriSelected.setOnClickListener {
            binding.itemHabitFriUnselected.visibility = View.VISIBLE
            binding.itemHabitFriSelected.visibility = View.GONE
            tempWeek.fri = false
            listener.onWeekClick(adapterPosition ,tempWeek)
        }

        binding.itemHabitSatUnselected.setOnClickListener {
            binding.itemHabitSatUnselected.visibility = View.GONE
            binding.itemHabitSatSelected.visibility = View.VISIBLE
            tempWeek.sat = true
            listener.onWeekClick(adapterPosition ,tempWeek)
        }

        binding.itemHabitSatSelected.setOnClickListener {
            binding.itemHabitSatUnselected.visibility = View.VISIBLE
            binding.itemHabitSatSelected.visibility = View.GONE
            tempWeek.sat = false
            listener.onWeekClick(adapterPosition ,tempWeek)
        }

        binding.itemHabitSunUnselected.setOnClickListener {
            binding.itemHabitSunUnselected.visibility = View.GONE
            binding.itemHabitSunSelected.visibility = View.VISIBLE
            tempWeek.sun = true
            listener.onWeekClick(adapterPosition ,tempWeek)
        }

        binding.itemHabitSunSelected.setOnClickListener {
            binding.itemHabitSunUnselected.visibility = View.VISIBLE
            binding.itemHabitSunSelected.visibility = View.GONE
            tempWeek.sun = false
            listener.onWeekClick(adapterPosition ,tempWeek)
        }

        binding.itemHabitStartdayPickerEt.setOnClickListener {
            listener.onStartDayClick(adapterPosition)
        }

        binding.itemHabitEnddayPickerEt.setOnClickListener {
            Log.d("HabitAddViewHolder" , "${adapterPosition}  포지션")
            listener.onEndDayClick(adapterPosition)
        }

        binding.itemHabitDeleteBtn.setOnClickListener {
            listener.deleteHabit(adapterPosition)
        }

        //입력 했을 때 변수 바꿔야함 -> 그리고 그 변수들이 모두 True일 때 해당 Item의 isFill이 바뀌어야함함
        binding.itemHabitAddInputEt.addTextChangedListener{

        }
    }

    fun changeIsFill(item: Habit){
        item.isFill = isTitleFill && isStartDayFill && isEndDayFill && isWeekFill
    }
}
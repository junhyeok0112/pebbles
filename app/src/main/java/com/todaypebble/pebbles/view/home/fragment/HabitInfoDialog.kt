package com.todaypebble.pebbles.view.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.data.remote.model.Habit
import com.todaypebble.pebbles.databinding.DialogHabitInfoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HabitInfoDialog(val habit : Habit) : BottomSheetDialogFragment(){

    private var _binding: DialogHabitInfoBinding? = null
    protected val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.dialog_habit_info, container, false)

        binding.habit = this.habit
        var week : String =""
        if(habit.weeks.mon) week +="월 "
        if(habit.weeks.tue) week +="화 "
        if(habit.weeks.wed) week +="수 "
        if(habit.weeks.thu) week +="목 "
        if(habit.weeks.fri) week +="금 "
        if(habit.weeks.sat) week +="토 "
        if(habit.weeks.sun) week +="일"

        binding.habitInfoDayTv.text = week

        return binding.root

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
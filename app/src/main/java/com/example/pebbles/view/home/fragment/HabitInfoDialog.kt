package com.example.pebbles.view.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.pebbles.R
import com.example.pebbles.data.remote.model.Habit
import com.example.pebbles.databinding.DialogHabitInfoBinding
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

        return binding.root

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.example.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.pebbles.R
import com.example.pebbles.databinding.CustomViewCalendarBinding
import java.util.*

class CustomCalendarView(
    context: Context,
    attrs: AttributeSet?
) : ConstraintLayout(context, attrs) {
    private val binding: CustomViewCalendarBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.custom_view_calendar,
            this,
            true
        )
    private val rowSize = 6
    private val colSize = 7

    private val calendarArray = Array(rowSize) { IntArray(colSize) { -1 } }

    init {
        val today = Date()
        setToday(today)
    }

    fun setToday(date: Date) {
        // Todo calculate Date

        update()
    }

    private fun update() {

        with(binding){
            cw1.setWeek(calendarArray[0])
            cw2.setWeek(calendarArray[1])
            cw3.setWeek(calendarArray[2])
            cw4.setWeek(calendarArray[3])
            cw5.setWeek(calendarArray[4])
            cw6.setWeek(calendarArray[5])
        }

        hideAllLine()
        if (calendarArray[1][0] == -1) showOneLine()
        else if (calendarArray[2][0] == -1) showTwoLine()
        else if (calendarArray[3][0] == -1) showThreeLine()
        else if (calendarArray[4][0] == -1) showFourLine()
        else if (calendarArray[5][0] == -1) showFiveLine()
        else showAllLine()
    }

    private fun hideAllLine() {
        with(binding) {
            cw1.isGone = true
            cw2.isGone = true
            cw3.isGone = true
            cw4.isGone = true
            cw5.isGone = true
            cw6.isGone = true
        }
    }

    private fun showOneLine() {
        binding.cw1.isVisible = true
    }

    private fun showTwoLine() {
        binding.cw2.isVisible = true
        showOneLine()
    }

    private fun showThreeLine() {
        binding.cw3.isVisible = true
        showTwoLine()
    }

    private fun showFourLine() {
        binding.cw4.isVisible = true
        showThreeLine()
    }

    private fun showFiveLine() {
        binding.cw5.isVisible = true
        showFourLine()
    }

    private fun showAllLine() {
        binding.cw6.isVisible = true
        showFiveLine()
    }
}
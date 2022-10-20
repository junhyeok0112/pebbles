package com.example.calendar.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.example.pebbles.R
import com.example.pebbles.databinding.ItemCalendarWeekBinding

internal class CustomWeek(
    context: Context,
    attrs: AttributeSet?
) : LinearLayout(context, attrs) {
    private val binding: ItemCalendarWeekBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_calendar_week,
            this,
            true
        )

    fun setWeek(weekArray: IntArray) {
        if (weekArray.size != 7) return

        with(binding) {
            cdSaturday.setDay(weekArray[0])
            cdMonday.setDay(weekArray[1])
            cdTuesday.setDay(weekArray[2])
            cdWednesday.setDay(weekArray[3])
            cdThursday.setDay(weekArray[4])
            cdFriday.setDay(weekArray[5])
            cdSaturday.setDay(weekArray[6])
        }
    }
}
package com.example.calendar.item

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

internal class CustomDay(
    context: Context,
    attrs: AttributeSet?
) : AppCompatTextView(context, attrs) {

    fun setDay(day: Int) {
        this.text = day.toString()
    }
}
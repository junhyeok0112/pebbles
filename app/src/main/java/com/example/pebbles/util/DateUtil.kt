package com.example.pebbles.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtil {

    fun monthYearFromDate(date:LocalDate) : String{
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월")

        return date.format(formatter)
    }
}
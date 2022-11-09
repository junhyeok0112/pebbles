package com.todaypebble.pebbles.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtil {
    //해당 년 월 나타내기 (2014년 2월)
    fun monthYearFromDate(date:LocalDate) : String{
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월")

        return date.format(formatter)
    }

    //해당 년월 나타내기 (2022-11)
    fun yearMonthFromDate(date:LocalDate) : String{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM")
        return date.format(formatter)
    }

}
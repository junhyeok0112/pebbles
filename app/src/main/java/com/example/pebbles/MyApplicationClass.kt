package com.example.pebbles

import android.app.Application
import java.time.LocalDate

class MyApplicationClass : Application(){
    companion object{
        //현재 내가 클릭한 날짜 저장 , 처음에는 오늘 날짜
        var clickedDate = LocalDate.now()


    }
}
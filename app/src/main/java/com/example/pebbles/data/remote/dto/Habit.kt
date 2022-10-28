package com.example.pebbles.data.remote.model

import com.example.pebbles.data.remote.dto.Todo


data class Habit(
    val cons_days : Int,    //연속 일자
    val end : String,       //종료일
    val id: Int,            //해빗 ID
    val name: String,       //해빗 이름
    val seq: Int,           //해빗 순서
    val start : String,     //시작일
    val status : String,    //완료 여부 -> 해빗이 가진 조약돌 갯수 다 채웠는지
    val time: String,       //해빗 시간
    val today : String,             //해빗 실천하는 날짜
    var today_status : String,      //해빗 실천하는 날짜의 완료 여뷰
    val todos : ArrayList<Todo>,         //해빗의 todos
    val week: String                //해빗 요일
)

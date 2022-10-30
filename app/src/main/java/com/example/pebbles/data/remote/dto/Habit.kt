package com.example.pebbles.data.remote.model

import com.example.pebbles.data.remote.dto.Todo
import com.google.gson.annotations.SerializedName


data class Habit(
    @SerializedName("cons_days") val cons_days : Int,    //연속 일자
    @SerializedName("end") val end : String,       //종료일
    @SerializedName("id") val id: Int,            //해빗 ID
    @SerializedName("name") val name: String,       //해빗 이름
    @SerializedName("seq") val seq: Int,           //해빗 순서
    @SerializedName("start") val start : String,     //시작일
    @SerializedName("status") val status : String,    //완료 여부 -> 해빗이 가진 조약돌 갯수 다 채웠는지
    @SerializedName("time") val time: String,       //해빗 시간
    @SerializedName("today") val today : String,             //해빗 실천하는 날짜
    @SerializedName("today_status") var today_status : String,      //해빗 실천하는 날짜의 완료 여뷰
    @SerializedName("todos") val todos : ArrayList<Todo>,         //해빗의 todos
    @SerializedName("week") val week: String                //해빗 요일
)

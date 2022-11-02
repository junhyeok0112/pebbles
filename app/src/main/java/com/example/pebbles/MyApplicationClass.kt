package com.example.pebbles

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp
import java.time.LocalDate

@HiltAndroidApp
class MyApplicationClass : Application() {

    companion object {
        const val X_ACCESS_TOKEN: String = "X-ACCESS-TOKEN"         // JWT Token Key
        const val NICKNAME: String = "NICKNAME"
        const val PASSWORD: String = "PASSWORD"
        const val USER_IDX: String = "USER_IDX"                     //유저 번호
        const val TAG: String = "TEMPLATE-APP"                      // Log, SharedPreference
        const val AUTO_LOGIN: String = "AUTO_LOGIN"

        lateinit var mSharedPreferences: SharedPreferences

        //현재 내가 클릭한 날짜 저장 , 처음에는 오늘 날짜
        var clickedDate = LocalDate.now()
        var userId = 2

        //데이터 베이스 초기화

    }

    override fun onCreate() {
        super.onCreate()
        mSharedPreferences = applicationContext.getSharedPreferences(TAG, Context.MODE_PRIVATE)
    }
}
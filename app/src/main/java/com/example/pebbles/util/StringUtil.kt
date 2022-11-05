package com.example.pebbles.util

import java.util.regex.Pattern

object StringUtil {

    val idPattern = Pattern.compile("^[a-zA-Z0-9]*\$")
    val pwPattern =
        Pattern.compile("^.*(?=^.{0,16}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$")


    //아이디 특수문자 체크하는 함수
    fun chkId(str: String?): Boolean {
        val matcherId = str?.let { idPattern.matcher(it) }
        return matcherId!!.find()

    }

    //비밀번호 유효성 검사
    fun chkPw(str: String?): Boolean {
        val matcherPw = str?.let { pwPattern.matcher(it) }
        return matcherPw!!.find()
    }


}
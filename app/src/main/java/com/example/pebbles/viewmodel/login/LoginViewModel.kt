package com.example.pebbles.viewmodel.login

import androidx.databinding.Bindable
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//AndroidViewModel 상속시 생성자 파라미터에 값 넣어주어야함 →context 등을 받아야할 경우 AndroidViewModel을 사용합니다.
class LoginViewModel : ViewModel(){

    val nickname = MutableLiveData<String>().apply { value = "" }
    fun getNicknameLen() :Int?{
        return nickname.value?.length
    }
}
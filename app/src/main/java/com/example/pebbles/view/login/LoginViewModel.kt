package com.example.pebbles.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//AndroidViewModel 상속시 생성자 파라미터에 값 넣어주어야함 →context 등을 받아야할 경우 AndroidViewModel을 사용합니다.
class LoginViewModel : ViewModel(){

    val _nickname = MutableLiveData<String>().apply { value = "" }
    val nickname :LiveData<String>
        get() = _nickname

    val _content = MutableLiveData<String>().apply { value = "" }
    val content : LiveData<String>
        get() = _content
}
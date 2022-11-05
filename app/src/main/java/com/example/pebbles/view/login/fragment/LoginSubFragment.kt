package com.example.pebbles.view.login.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.adapters.ViewGroupBindingAdapter.setListener
import androidx.fragment.app.activityViewModels
import com.bit.kodari.Config.BaseFragment
import com.example.pebbles.R
import com.example.pebbles.databinding.FragmentLoginSubBinding
import com.example.pebbles.view.login.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginSubFragment : BaseFragment<FragmentLoginSubBinding>(R.layout.fragment_login_sub) {

    private val loginViewModel : LoginViewModel by activityViewModels()

    override fun initAfterBinding() {
        binding.vm = loginViewModel
        binding.lifecycleOwner = this
        setObserver()
        setListener()
    }

    fun setObserver(){
        loginViewModel.login_id.observe(this, {
            if(it.length != 0 && loginViewModel.login_pw.value!!.length != 0){    //둘다 값이 있으면 true
                loginViewModel.canLogin.value = true
            }
        })

        loginViewModel.login_pw.observe(this,{
            if(it.length != 0 && loginViewModel.login_pw.value!!.length != 0){    //둘다 값이 있으면 true
                loginViewModel.canLogin.value = true
            }
        })
    }

    fun setListener(){
        binding.loginBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val loginResponse = loginViewModel.logIn()
                if(loginResponse.body()!!.isSuccess){
                    showToast("성공")
                    Log.d("Login_test" , "${loginResponse.body()}")
                } else{
                    showToast("실패")
                    Log.d("Login_test" , "${loginResponse.body()}")
                }
            }
        }
    }
}
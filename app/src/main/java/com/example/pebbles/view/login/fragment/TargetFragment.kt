package com.example.pebbles.view.login.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bit.kodari.Config.BaseFragment
import com.example.pebbles.R
import com.example.pebbles.databinding.FragmentTargetBinding
import com.example.pebbles.view.MainActivity
import com.example.pebbles.viewmodel.login.LoginViewModel

//목표 설정 프래그먼트
class TargetFragment : BaseFragment<FragmentTargetBinding>(R.layout.fragment_target) {

    private val loginViewModel : LoginViewModel by activityViewModels()

    override fun initAfterBinding() {
        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this

        setListener()
    }

    private fun setListener(){
        binding.targetCompleteBtn.setOnClickListener {
            startActivityWithClear(MainActivity::class.java)
        }
    }

//액티비티 이동
    fun startActivityWithClear(activity: Class<*>?) {
        val intent = Intent(context, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
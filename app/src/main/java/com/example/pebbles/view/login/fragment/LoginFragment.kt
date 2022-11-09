package com.example.pebbles.view.login.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bit.kodari.Config.BaseFragment
import com.example.pebbles.R
import com.example.pebbles.databinding.FragmentLoginBinding
import java.time.LocalDate


class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private lateinit var callback : OnBackPressedCallback
    //시스템 뒤로가기 눌렀을시 보이는 뷰페이저에 따라 다르게 작동하게 예외처리
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this ,callback)
    }

    override fun initAfterBinding() {
        setListener()
    }

    private fun setListener() {
        binding.loginStartBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.loginLoginTv.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_loginSubFragment)
        }
    }
}
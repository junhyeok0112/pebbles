package com.example.pebbles.view.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bit.kodari.Config.BaseFragment
import com.example.pebbles.R
import com.example.pebbles.databinding.FragmentLoginBinding


class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {


    override fun initAfterBinding() {
        setListener()
    }

    private fun setListener() {
        binding.loginKakaoBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }
}
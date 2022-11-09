package com.example.pebbles.view.login.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bit.kodari.Config.BaseFragment
import com.example.pebbles.R
import com.example.pebbles.databinding.FragmentSignUpBinding
import com.example.pebbles.view.login.adapter.SingUpVPAdapter


class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {

    private lateinit var callback : OnBackPressedCallback
//시스템 뒤로가기 눌렀을시 보이는 뷰페이저에 따라 다르게 작동하게 예외처리
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if(binding.signUpViewpager.currentItem == 1){
                    binding.signUpViewpager.setCurrentItem(0)
                } else{
                    view?.findNavController()?.navigate(R.id.action_signUpFragment_to_loginFragment)
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this ,callback)
    }


    override fun initAfterBinding() {
        setViewPager()
        setListener()
    }
//뷰페이저 2 설정 함수
    private fun setViewPager(){
        binding.signUpViewpager.adapter = SingUpVPAdapter(this)
        binding.signUpViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.signUpViewpager.offscreenPageLimit = 1
        binding.signUpViewpager.isUserInputEnabled = false
        binding.signUpViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0 -> { binding.signUpProgressBar.setProgressCompat(50, true) }
                    else -> { binding.signUpProgressBar.setProgressCompat(100,true) }
                }
            }
        })
    }

    private fun setListener(){
        binding.signUpBackIv.setOnClickListener {
            when(binding.signUpViewpager.currentItem){
              0 -> {
                  it.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
              }
              else -> {
                  binding.signUpViewpager.setCurrentItem(0)
              }
            }
        }
    }

}
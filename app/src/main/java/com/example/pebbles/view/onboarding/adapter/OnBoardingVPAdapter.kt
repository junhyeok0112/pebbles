package com.example.pebbles.view.onboarding.adapter

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pebbles.view.onboarding.OnBoardingFirstFragment
import com.example.pebbles.view.onboarding.OnBoardingSecondFragment
import com.example.pebbles.view.onboarding.OnBoardingThirdFragment

class OnBoardingVPAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    //항상 3개 존재
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                OnBoardingFirstFragment()
            }
            1 -> {
                OnBoardingSecondFragment()
            }
            2 -> {
                OnBoardingThirdFragment()
            }
            else -> {       //아무것도 선택 안됐을 때
                OnBoardingFirstFragment()
            }
        }
    }
}
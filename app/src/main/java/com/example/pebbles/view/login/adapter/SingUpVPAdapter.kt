package com.example.pebbles.view.login.adapter

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pebbles.view.login.fragment.NickNameFragment
import com.example.pebbles.view.login.fragment.TargetFragment

class SingUpVPAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
           0 -> {NickNameFragment()}
            else->{NickNameFragment()}
         }
    }
}
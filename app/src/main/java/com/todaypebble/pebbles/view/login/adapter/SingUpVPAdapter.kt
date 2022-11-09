package com.todaypebble.pebbles.view.login.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.todaypebble.pebbles.view.login.fragment.NickNameFragment
import com.todaypebble.pebbles.view.login.fragment.TermFragment

class SingUpVPAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
           0 -> {TermFragment()}
            else->{NickNameFragment()}
         }
    }
}
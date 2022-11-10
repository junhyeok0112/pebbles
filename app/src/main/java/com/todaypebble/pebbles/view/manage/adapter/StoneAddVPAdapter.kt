package com.todaypebble.pebbles.view.manage.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.todaypebble.pebbles.view.manage.fragment.HabitAddFragment
import com.todaypebble.pebbles.view.manage.fragment.SandAddFragment
import com.todaypebble.pebbles.view.manage.fragment.StoneNameAndDayFragment

class StoneAddVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                StoneNameAndDayFragment()
            }
            1 -> {
                HabitAddFragment()
            }
            else -> {
                SandAddFragment()
            }
        }
    }
}
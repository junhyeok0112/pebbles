package com.todaypebble.pebbles.view.manage.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bit.kodari.Config.BaseFragment
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.data.remote.dto.Todo
import com.todaypebble.pebbles.data.remote.dto.manage.MyStone
import com.todaypebble.pebbles.data.remote.model.Habit
import com.todaypebble.pebbles.databinding.FragmentManageBinding
import com.todaypebble.pebbles.view.home.adapter.HabitRVAdapter
import com.todaypebble.pebbles.view.home.fragment.HabitInfoDialog
import com.todaypebble.pebbles.view.manage.ManageViewModel
import com.todaypebble.pebbles.view.manage.adapter.HabitAddRVAdapter
import com.todaypebble.pebbles.view.manage.adapter.MyStoneRVAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageFragment : BaseFragment<FragmentManageBinding>(R.layout.fragment_manage) {

    private val manageViewModel : ManageViewModel by activityViewModels()

    override fun initAfterBinding() {
        initListener()
        setRecyclerView()
    }

    private fun initListener(){
        binding.manageFlaBtn.setOnClickListener {
//            manageViewModel.initStoneInfo()
            it.findNavController().navigate(R.id.action_manageFragment_to_stoneAddFragment)
        }


    }

    private fun setRecyclerView(){


        val stoneList = manageViewModel.stoneList.value as ArrayList<MyStone>?
        Log.d("테스트" , "${stoneList}")
        val adapter = MyStoneRVAdapter(stoneList!!)
        binding.manageListRv.adapter = adapter
    }
}
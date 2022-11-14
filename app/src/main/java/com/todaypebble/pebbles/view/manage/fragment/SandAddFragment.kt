package com.todaypebble.pebbles.view.manage.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bit.kodari.Config.BaseFragment
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.data.remote.dto.manage.Todo
import com.todaypebble.pebbles.databinding.FragmentSandAddBinding
import com.todaypebble.pebbles.view.manage.ManageViewModel
import com.todaypebble.pebbles.view.manage.adapter.HabitAddRVAdapter
import com.todaypebble.pebbles.view.manage.adapter.SandAddRVAdapter


class SandAddFragment : BaseFragment<FragmentSandAddBinding>(R.layout.fragment_sand_add) {

    private val manageViewModel : ManageViewModel by activityViewModels()
    private lateinit var adapter : SandAddRVAdapter
    override fun initAfterBinding() {

        setRecyclerView()

    }

    fun setRecyclerView(){

        val listener = object : SandAddRVAdapter.clickListener{
            override fun addClickTodo(position:Int) {
                manageViewModel.HabitList.value?.get(position)?.todos?.add(Todo("",manageViewModel.HabitList.value?.get(position)?.todos?.size!!))
//                adapter.notifyItemInserted()
            }

            override fun writeTodo(position: Int, habitPosition: Int, name :String) {
                manageViewModel.HabitList.value?.get(habitPosition)?.todos?.get(position)?.name = name
            }
        }

        Log.d("SandAdd" , "setRecyclerView()")
        val habitList = manageViewModel.HabitList
        Log.d("SandAdd" , "${habitList.value}")
        adapter = SandAddRVAdapter(habitList)
        adapter.setListener(listener)
        binding.sandAddRv.adapter = adapter
    }

}
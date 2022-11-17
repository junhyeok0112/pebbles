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
import com.todaypebble.pebbles.view.manage.DeleteTodoDialog
import com.todaypebble.pebbles.view.manage.ManageViewModel
import com.todaypebble.pebbles.view.manage.adapter.HabitAddRVAdapter
import com.todaypebble.pebbles.view.manage.adapter.SandAddRVAdapter


class SandAddFragment : BaseFragment<FragmentSandAddBinding>(R.layout.fragment_sand_add) {

    private val manageViewModel : ManageViewModel by activityViewModels()
    private lateinit var adapter : SandAddRVAdapter
    override fun initAfterBinding() {

        setResult()
        setRecyclerView()
        initListener()
        binding.sandAddNameTv.text = manageViewModel.stoneName.value

    }

    fun setRecyclerView(){

        val listener = object : SandAddRVAdapter.clickListener{
            override fun addClickTodo(position:Int) {
                manageViewModel.addTodo(position)
//                manageViewModel.HabitList.value?.get(position)?.todos?.add(Todo("",manageViewModel.HabitList.value?.get(position)?.todos?.size!!))
            }

            override fun writeTodo(position: Int, habitPosition: Int, name :String) {
                manageViewModel.writeTodo(position,habitPosition,name)
//                manageViewModel.HabitList.value?.get(habitPosition)?.todos?.get(position)?.name = name
            }

            override fun deleteTodo(position: Int, habitPosition: Int) {
                //다이얼로그 띄워야함 ..
                val deleteTodoDialog = DeleteTodoDialog(position, habitPosition)
                deleteTodoDialog.show(requireActivity().supportFragmentManager , "DeleteTodoDialog")

            }
        }

        Log.d("SandAdd" , "setRecyclerView()")
        val habitList = manageViewModel.HabitList
        Log.d("SandAdd" , "${habitList.value}")
        adapter = SandAddRVAdapter(habitList)
        adapter.setListener(listener)
        binding.sandAddRv.adapter = adapter
    }


    private fun initListener(){

    }


    //삭제 다이얼로그에서 넘어온 값을 확인하고 실행
    fun setResult(){
        requireActivity().supportFragmentManager.setFragmentResultListener("request",this
        ) { key, bundle ->
            if (key == "request") {
                if (bundle.containsKey("delete")) {       //삭제되었다고 하고 넘어오면
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}
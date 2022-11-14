package com.todaypebble.pebbles.view.manage.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.bit.kodari.Config.BaseFragment
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.FragmentStoneAddResultBinding
import com.todaypebble.pebbles.view.manage.ManageViewModel
import com.todaypebble.pebbles.view.manage.adapter.StoneAddResultAdapter

class StoneAddResultFragment : BaseFragment<FragmentStoneAddResultBinding>(R.layout.fragment_stone_add_result) {

    private val manageViewModel : ManageViewModel by activityViewModels()

    override fun initAfterBinding() {
        //뷰 모델의 정보들 세팅하기 -> 중첩 리싸이클러 뷰 필요
        init()
    }

    fun init(){
        binding.stoneAddResultNameTv.text = manageViewModel.stoneName.value
        setRecyclerView()
        initListener()
    }

    fun setRecyclerView(){
        val habitList = manageViewModel.HabitList.value
        val adapter = StoneAddResultAdapter(habitList!!)
        binding.stoneAddResultRv.adapter = adapter
    }

    fun initListener(){
        binding.stoneAddResultCloseIv.setOnClickListener {  //바윗돌 관리 화면으로 돌아가기
            view?.findNavController()?.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        manageViewModel.initStoneInfo() //초기화
    }
}
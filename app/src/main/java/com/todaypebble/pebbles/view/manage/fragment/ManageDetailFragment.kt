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
import com.todaypebble.pebbles.databinding.FragmentManageDetailBinding
import com.todaypebble.pebbles.view.manage.ManageViewModel
import com.todaypebble.pebbles.view.manage.adapter.DetailMyStoneRVAdapter


class ManageDetailFragment : BaseFragment<FragmentManageDetailBinding>(R.layout.fragment_manage_detail) {

    private val manageViewModel : ManageViewModel by activityViewModels()

    override fun initAfterBinding() {
        binding.detailMyStone = manageViewModel.detailStone

        setRecyclerView()
        initListener()
    }

    private fun setRecyclerView(){
        //뷰모델의 detailStone 이용
        val list = manageViewModel.detailStone.getRockManageDetailHabitResList
        val adapter = DetailMyStoneRVAdapter(list)
        binding.manageDetailRv.adapter = adapter
    }

    private fun initListener(){
        binding.manageDetailBackBtn.setOnClickListener {
            view?.findNavController()?.popBackStack()
        }
    }
}
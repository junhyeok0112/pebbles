package com.todaypebble.pebbles.view.manage.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.bit.kodari.Config.BaseFragment
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.FragmentManageBinding


class ManageFragment : BaseFragment<FragmentManageBinding>(R.layout.fragment_manage) {

    override fun initAfterBinding() {
        initListener()
    }

    private fun initListener(){
        binding.manageFlaBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_manageFragment_to_stoneAddFragment)
        }
    }
}
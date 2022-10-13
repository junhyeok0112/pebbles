package com.example.pebbles.view.home.fragment

import androidx.fragment.app.viewModels
import com.bit.kodari.Config.BaseFragment
import com.example.pebbles.R
import com.example.pebbles.databinding.FragmentHomeBinding
import com.example.pebbles.view.home.HomeViewModel
import com.example.pebbles.view.home.adapter.HabitRVAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun initAfterBinding() {
        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner = this

        setRecycelrView()


    }

    fun setRecycelrView(){
        val adapter = HabitRVAdapter(homeViewModel.habitList)
        binding.homeRecyclerRv.adapter = adapter
    }
}
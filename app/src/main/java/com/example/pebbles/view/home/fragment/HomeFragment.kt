package com.example.pebbles.view.home.fragment

import androidx.fragment.app.viewModels
import com.bit.kodari.Config.BaseFragment
import com.example.pebbles.R
import com.example.pebbles.data.remote.dto.Todo
import com.example.pebbles.data.remote.model.Habit
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

        val listener = object : HabitRVAdapter.TodoButtonListener{
            override fun onSandClick(item: Todo , habit_id :Int) {
                //뷰모델에서 데이터 바꾸는 코드 호출하기
                homeViewModel.changeTodo(item , habit_id)
            }

            override fun onDetailClick(item: Habit) {
                //해당 정보를 가지고 BottomSheetDialog 실행.
                showToast("클릭")
            }
        }

        val adapter = HabitRVAdapter(homeViewModel.habitList)
        adapter.setListener(listener)
        binding.homeRecyclerRv.adapter = adapter
    }
}
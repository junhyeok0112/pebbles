package com.todaypebble.pebbles.view.home.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bit.kodari.Config.BaseFragment
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.data.remote.dto.Todo
import com.todaypebble.pebbles.data.remote.model.Habit
import com.todaypebble.pebbles.databinding.FragmentHomeBinding
import com.todaypebble.pebbles.view.home.HomeViewModel
import com.todaypebble.pebbles.view.home.adapter.HabitRVAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding> (R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapter : HabitRVAdapter

    override fun initAfterBinding() {
        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner = this

        setRecycelrView()
        initListener()

        homeViewModel.habitList.observe(this,{
            adapter.notifyDataSetChanged()
            Log.d("HABIT_LIST" , "${homeViewModel.habitList.value}")
        })

    }


    fun setRecycelrView(){

        val listener = object : HabitRVAdapter.TodoButtonListener{
            override fun onSandClick(item: Todo , habit_id :Int) {
                if(LocalDate.now().toString() < homeViewModel.habitList.value?.get(0)?.today!!){
                    showToast("미래 일은 아직 할 수 없습니다")
                }else{
                    //뷰모델에서 데이터 바꾸는 코드 호출하기
                    homeViewModel.changeTodo(item , habit_id)
                }
            }

            override fun onDetailClick(item: Habit) {
                //해당 정보를 가지고 BottomSheetDialog 실행.
                val bottomSheetDialog = HabitInfoDialog(item)
                bottomSheetDialog.show(requireActivity().supportFragmentManager , "BottomSheetDialog")
            }
        }

        adapter = HabitRVAdapter(homeViewModel.habitList)
        adapter.setHasStableIds(true)
        adapter.setListener(listener)
        binding.homeRecyclerRv.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        homeViewModel.updateHabits()
    }

    private fun initListener(){
        binding.homeSettingIv.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
        }
    }

}

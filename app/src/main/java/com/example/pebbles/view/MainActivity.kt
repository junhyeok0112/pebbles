package com.example.pebbles.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bit.kodari.Config.BaseActivity
import com.example.pebbles.R
import com.example.pebbles.data.remote.dto.HabitList
import com.example.pebbles.databinding.ActivityMainBinding
import com.example.pebbles.network.RetrofitInstance
import com.example.pebbles.data.remote.api.HomeService
import com.example.pebbles.util.saveLoginInfo
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initAfterBinding() {
//리스너 초기화
        binding.mainBottomNavigationView.itemIconTintList = null

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment_controller) as NavHostFragment
        val navController = navHostFragment.navController
//네비게이션과 바텀네비 연결
        NavigationUI.setupWithNavController(binding.mainBottomNavigationView, navController)
        initListener(navController)

        //Retrofit Test
        val homeService = RetrofitInstance.getRetrofit.create(HomeService::class.java)

        val responseLiveData : LiveData<Response<HabitList>> = liveData{
                    val response = homeService.getHabits("eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWQiOjEsImlhdCI6MTY2NzM3NzYzOCwiZXhwIjoxNjY4MjQxNjM4fQ.ENx8Ts5JqT4mdz-5nA-wphWUh2Nv_p3oO0SFCLOay1Y" ,1)
            emit(response)
        }

        responseLiveData.observe(this, Observer {
            val list = it.body()?.toString()
            Log.d("Retrofit_Test" , it.code().toString())
            Log.d("Retrofit_Test" , list.toString())

        })

        //SharePreferenc에 일단 값 저장
        saveLoginInfo("eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWQiOjEsImlhdCI6MTY2NzM3NzYzOCwiZXhwIjoxNjY4MjQxNjM4fQ.ENx8Ts5JqT4mdz-5nA-wphWUh2Nv_p3oO0SFCLOay1Y","yj","123",1)

    }

    private fun initListener(navController: NavController) {

//바텀네비게이션 뷰 선택 시 이벤트 발생
        navController.addOnDestinationChangedListener{_,destination, _ ->
            if(destination.id == R.id.homeFragment){
                binding.mainBottomNavigationView.menu.findItem(R.id.homeFragment).setIcon(R.drawable.ic_home_select)
                binding.mainBottomNavigationView.menu.findItem(R.id.achiveFragment).setIcon(R.drawable.ic_achive_unselect)
                binding.mainBottomNavigationView.menu.findItem(R.id.manageFragment).setIcon(R.drawable.ic_manage_unselect)
            } else if(destination.id == R.id.manageFragment){
                binding.mainBottomNavigationView.menu.findItem(R.id.manageFragment).setIcon(R.drawable.ic_manage_select)
                binding.mainBottomNavigationView.menu.findItem(R.id.homeFragment).setIcon(R.drawable.ic_home_unselect)
                binding.mainBottomNavigationView.menu.findItem(R.id.achiveFragment).setIcon(R.drawable.ic_achive_unselect)
            } else{
                binding.mainBottomNavigationView.menu.findItem(R.id.achiveFragment).setIcon(R.drawable.ic_achive_select)
                binding.mainBottomNavigationView.menu.findItem(R.id.homeFragment).setIcon(R.drawable.ic_home_unselect)
                binding.mainBottomNavigationView.menu.findItem(R.id.manageFragment).setIcon(R.drawable.ic_manage_unselect)
            }
        }
    }

}


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
import retrofit2.Response

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
            val response = homeService.getHabits(2 ,"eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWQiOjIsImlhdCI6MTY2NzEyMzU4MiwiZXhwIjoxNjY3OTg3NTgyfQ.vExDhlVDlP7mN-Ap8Nzp67FdYR1EpIY0JHAc_Cd3IWQ" )
            emit(response)
        }

        responseLiveData.observe(this, Observer {
            val list = it.body()?.toString()
            Log.d("Retrofit_Test" , it.code().toString())
            Log.d("Retrofit_Test" , list.toString())

        })

        //SharePreferenc에 일단 값 저장
        saveLoginInfo("eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWQiOjIsImlhdCI6MTY2NzEyMzU4MiwiZXhwIjoxNjY3OTg3NTgyfQ.vExDhlVDlP7mN-Ap8Nzp67FdYR1EpIY0JHAc_Cd3IWQ","yj","123",2)

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


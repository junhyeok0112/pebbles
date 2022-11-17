package com.todaypebble.pebbles.view

import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bit.kodari.Config.BaseActivity
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.ActivityMainBinding
import com.todaypebble.pebbles.view.login.LoginViewModel
import com.todaypebble.pebbles.view.manage.ManageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val manageViewModel : ManageViewModel by viewModels()


    override fun initAfterBinding() {
//리스너 초기화
        binding.mainBottomNavigationView.itemIconTintList = null

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment_controller) as NavHostFragment
        val navController = navHostFragment.navController
//네비게이션과 바텀네비 연결
        NavigationUI.setupWithNavController(binding.mainBottomNavigationView, navController)
        initListener(navController)
        binding.manageVM = manageViewModel
        binding.lifecycleOwner = this

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

            //셋팅 네비게이션 가면 바텀 네비게이션 뷰 숨기기
            if(destination.id == R.id.settingFragment || destination.id == R.id.withdrawalFragment || destination.id == R.id.stoneAddFragment || destination.id == R.id.manageDetailFragment){
                binding.mainBottomNavigationView.visibility = View.GONE
            } else{
                binding.mainBottomNavigationView.visibility = View.VISIBLE
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("Main Activity" , "onPause()")
    }

}


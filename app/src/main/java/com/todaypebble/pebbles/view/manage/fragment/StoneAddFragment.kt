package com.todaypebble.pebbles.view.manage.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bit.kodari.Config.BaseFragment
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.FragmentStoneAddBinding
import com.todaypebble.pebbles.view.login.adapter.SingUpVPAdapter
import com.todaypebble.pebbles.view.manage.ManageViewModel
import com.todaypebble.pebbles.view.manage.adapter.StoneAddVPAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//뷰페이저 있는 곳 ->여기서 StoneNick -> HabitAdd -> sandAdd -> Result 순으로
class StoneAddFragment : BaseFragment<FragmentStoneAddBinding>(R.layout.fragment_stone_add) {

    private val manageViewModel: ManageViewModel by activityViewModels()
    private lateinit var callback: OnBackPressedCallback
    private var index = 0

    //시스템 뒤로가기 눌렀을시 보이는 뷰페이저에 따라 다르게 작동하게 예외처리
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (index == 1) {
//                    binding.stoneAddViewpager.setCurrentItem(0)
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.stone_add_container_cl, StoneNameAndDayFragment().apply {

                        }).commit()
                    binding.stoneAddProgressBar.setProgressCompat(33, true)
                    binding.stoneAddNextBtn.text = "다음"
                    index = 0
                } else if (index == 2) {
//                    binding.stoneAddViewpager.setCurrentItem(1)
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.stone_add_container_cl, HabitAddFragment().apply {

                        }).commit()
                    binding.stoneAddProgressBar.setProgressCompat(66, true)
                    binding.stoneAddNextBtn.text = "다음"
                    index = 1
                } else {
                    view?.findNavController()?.popBackStack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

        //상태바 색상 변경
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity?.window?.statusBarColor = Color.parseColor("#FFFFFF")
    }

    override fun initAfterBinding() {
//        setViewPager()

        //프래그먼트 셋팅
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.stone_add_container_cl, StoneNameAndDayFragment().apply {

            }).commit()
        binding.stoneAddProgressBar.setProgressCompat(33, true)
        setListener()
    }

//    //뷰페이저 2 설정 함수
//    private fun setViewPager() {
//        binding.stoneAddViewpager.adapter = StoneAddVPAdapter(this)
//        binding.stoneAddViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//        binding.stoneAddViewpager.isUserInputEnabled = false
//        binding.stoneAddViewpager.registerOnPageChangeCallback(object :
//            ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                when (position) {
//                    0 -> {
//                        binding.stoneAddProgressBar.setProgressCompat(33, true)
//                        binding.stoneAddTitleTv.text = "바윗돌 쌓기"
//                    }
//                    1 -> {
//                        binding.stoneAddProgressBar.setProgressCompat(66, true)
//                        binding.stoneAddTitleTv.text = "조약돌로 쪼개기"
//                    }
//                    else -> {
//                        binding.stoneAddProgressBar.setProgressCompat(100, true)
//                        binding.stoneAddTitleTv.text = "모래알로 쪼개기"
//                    }
//                }
//            }
//        })
//    }


    private fun setListener() {
        binding.stoneAddBackIv.setOnClickListener {
            when (index) {
                0 -> {
                    it.findNavController().popBackStack()           //쌓여있는 스택으로
                    index = 0
                }
                1 -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.stone_add_container_cl, StoneAddFragment().apply {

                        }).commit()
                    index = 0
                    binding.stoneAddProgressBar.setProgressCompat(33, true)
                    binding.stoneAddNextBtn.text = "다음"
                    binding.stoneAddSkipTv.visibility = View.GONE
                }
                else -> {
                    index = 1
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.stone_add_container_cl, HabitAddFragment().apply {

                        }).commit()
                    binding.stoneAddProgressBar.setProgressCompat(66, true)
                    binding.stoneAddNextBtn.text = "다음"
                    binding.stoneAddSkipTv.visibility = View.GONE
//                    binding.stoneAddViewpager.setCurrentItem(1)
                }
            }
        }

        binding.stoneAddNextBtn.setOnClickListener {
            when (index) {
                0 -> {
//                    binding.stoneAddViewpager.setCurrentItem(1)        //쌓여있는 스택으로
                    index = 1
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.stone_add_container_cl, HabitAddFragment().apply {

                        }).commit()
                    binding.stoneAddProgressBar.setProgressCompat(66, true)
                }

                1 -> {

//                    binding.stoneAddViewpager.setCurrentItem(2)
                    index = 2
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.stone_add_container_cl, SandAddFragment().apply {

                        }).commit()
                    binding.stoneAddProgressBar.setProgressCompat(100, true)
                    binding.stoneAddNextBtn.text = "완료"
                    binding.stoneAddSkipTv.visibility = View.VISIBLE
                }
                2 -> {   //완료 눌렀을 때 Viewmodel에서 가공 후 데이터 보내야함 -> 성공시 넘어가게
                    //여기서 API 호출
                    CoroutineScope(Dispatchers.IO).launch {
                        val response = manageViewModel.makeNewStone()
                        Log.d("MakeStone" , "${response}")
                        withContext(Dispatchers.Main){
                            if(response.code == 1000){
                                //성공했을 때
                                index = 3
                                binding.stoneAddNextBtn.text = "홈으로 가기"
                                binding.stoneAddBackIv.visibility = View.GONE
                                binding.stoneAddSkipTv.visibility = View.GONE
                                binding.stoneAddProgressBar.visibility = View.GONE
                                binding.stoneAddTitleTv.visibility = View.GONE
                                requireActivity().supportFragmentManager.beginTransaction()
                                    .replace(R.id.stone_add_container_cl, StoneAddResultFragment().apply {

                                    }).commit()
                                manageViewModel.updateStoneList()   //새로운 돌 리스트로 가져옴
                            } else{
                                Log.d("MakeStone" , "${response}")
                                showToast("하이라이트 생성 실패")
                            }
                        }
                    }

                }
                else->{ //홈 화면으로 돌아가게 하기 -> 바윗돌 관리 화면으로.
                    view?.findNavController()?.popBackStack()
                }
            }
        }

        //완료 눌렀을 때와 동일하게.
        binding.stoneAddSkipTv.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val response = manageViewModel.makeNewStone()
                withContext(Dispatchers.Main){
                    if(response.code == 1000){
                        //성공했을 때
                        index = 3
                        binding.stoneAddNextBtn.text = "홈으로 가기"
                        binding.stoneAddBackIv.visibility = View.GONE
                        binding.stoneAddSkipTv.visibility = View.GONE
                        binding.stoneAddProgressBar.visibility = View.GONE
                        binding.stoneAddTitleTv.visibility = View.GONE
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.stone_add_container_cl, StoneAddResultFragment().apply {

                            }).commit()
                    } else{
                        Log.d("MakeStone" , "${response}")
                        showToast("하이라이트 생성 실패")
                    }
                }
            }
        }

    }
}
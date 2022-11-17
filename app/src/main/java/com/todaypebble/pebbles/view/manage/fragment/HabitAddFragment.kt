package com.todaypebble.pebbles.view.manage.fragment

import android.app.DatePickerDialog
import android.util.Log
import androidx.fragment.app.activityViewModels
import com.bit.kodari.Config.BaseFragment
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.data.remote.dto.manage.Weeks
import com.todaypebble.pebbles.databinding.FragmentHabitAddBinding
import com.todaypebble.pebbles.view.manage.DeleteHabitDialog
import com.todaypebble.pebbles.view.manage.ManageViewModel
import com.todaypebble.pebbles.view.manage.adapter.HabitAddRVAdapter
import java.text.SimpleDateFormat
import java.util.*


class HabitAddFragment : BaseFragment<FragmentHabitAddBinding>(R.layout.fragment_habit_add) {

    private val manageViewModel: ManageViewModel by activityViewModels()
    private lateinit var adapter: HabitAddRVAdapter

    //하루 할일 추가 누르면 -> Todos가 비어있는 Habit이 추가되어야함
    //그 추가된 Habit들과 연결해야함 .. 흠 ..
    override fun initAfterBinding() {
        binding.vm = manageViewModel
        binding.lifecycleOwner = this

        setResult()
        initListener()
        initRecyclerView()




    }

    //삭제 다이얼로그에서 넘어온 값을 확인하고 실행
    fun setResult(){
        requireActivity().supportFragmentManager.setFragmentResultListener("request",this
        ) { key, bundle ->
            if (key == "request") {
                if (bundle.containsKey("delete")) {       //삭제되었다고 하고 넘어오면
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    fun initListener() {
        binding.habitAddAddHabitBtn.setOnClickListener {
            if(manageViewModel.HabitList.value!!.size >=3){
                showToast("3개 이상 추가 할 수 없습니다")
            } else{
                manageViewModel.addHabit()
                adapter.notifyDataSetChanged()
            }
        }

        binding.habitAddAskTv.setOnClickListener {
            manageViewModel.showInfo()
        }

    }

    fun initRecyclerView() {
        val listener = object : HabitAddRVAdapter.clickListener{
            override fun onWeekClick(position: Int, tempWeeks: Weeks) {
                manageViewModel.HabitList.value?.get(position)?.weeks = tempWeeks
                Log.d("변경", "${manageViewModel.HabitList.value?.get(position)?.weeks}")
            }

            override fun onStartDayClick(position:Int) {
                val datepickercalendar = Calendar.getInstance()
                val year = datepickercalendar.get(Calendar.YEAR)
                val month = datepickercalendar.get(Calendar.MONTH)
                val day = datepickercalendar.get(Calendar.DAY_OF_MONTH)

                val dpd = DatePickerDialog(
                    requireContext(),
                    R.style.MySpinnerDatePickerStyle,
                    { _, year, monthOfYear, dayOfMonth ->
//                  월이 0부터 시작하여 1을 더해주어야함
                        val month = monthOfYear + 1
//                   선택한 날짜의 요일을 구하기 위한 calendar
                        val calendar = Calendar.getInstance()
//                    선택한 날짜 세팅
                        calendar.set(year, monthOfYear, dayOfMonth)
                        val date = calendar.time
                        val simpledateformat = SimpleDateFormat("EEEE", Locale.getDefault())
                        val dayName: String = simpledateformat.format(date)
                        manageViewModel.HabitList.value?.get(position)?.start = "$year-$month-$dayOfMonth"
                        adapter.notifyDataSetChanged()
                    },
                    year,
                    month,
                    day
                )
//           최소 날짜를 현재 시각 이후로
                dpd.datePicker.minDate = System.currentTimeMillis() - 1000;
                //String -> Date -> Calendar 형식으로 변경
                val maxDate = Calendar.getInstance()
                val yearMax = manageViewModel.stoneEndDay.value?.substring(0,4)
                val monthMax = manageViewModel.stoneEndDay.value?.substring(5,7)
                val dayMax = manageViewModel.stoneEndDay.value?.substring(8,10)
                maxDate.set(yearMax!!.toInt(), monthMax!!.toInt()-1, dayMax!!.toInt())      //-1 해야지 원하는 ㄴ달 나옴
                dpd.datePicker.maxDate = maxDate.timeInMillis
                dpd.show()
            }

            override fun onEndDayClick(position: Int) {
                val datepickercalendar = Calendar.getInstance()
                val year = datepickercalendar.get(Calendar.YEAR)
                val month = datepickercalendar.get(Calendar.MONTH)
                val day = datepickercalendar.get(Calendar.DAY_OF_MONTH)

                val dpd = DatePickerDialog(
                    requireContext(),
                    R.style.MySpinnerDatePickerStyle,
                    { _, year, monthOfYear, dayOfMonth ->
//                  월이 0부터 시작하여 1을 더해주어야함
                        val month = monthOfYear + 1
//                   선택한 날짜의 요일을 구하기 위한 calendar
                        val calendar = Calendar.getInstance()
//                    선택한 날짜 세팅
                        calendar.set(year, monthOfYear, dayOfMonth)
                        val date = calendar.time
                        val simpledateformat = SimpleDateFormat("EEEE", Locale.getDefault())
                        val dayName: String = simpledateformat.format(date)
                        manageViewModel.HabitList.value?.get(position)?.end = "$year-$month-$dayOfMonth"
                        adapter.notifyDataSetChanged()
                    },
                    year,
                    month,
                    day
                )
//           최소 날짜를 현재 시각 이후로
                dpd.datePicker.minDate = System.currentTimeMillis() - 1000;
                val maxDate = Calendar.getInstance()
                val yearMax = manageViewModel.stoneEndDay.value?.substring(0,4)
                val monthMax = manageViewModel.stoneEndDay.value?.substring(5,7)
                val dayMax = manageViewModel.stoneEndDay.value?.substring(8,10)
                maxDate.set(yearMax!!.toInt(), monthMax!!.toInt()-1, dayMax!!.toInt())
                dpd.datePicker.maxDate = maxDate.timeInMillis
                dpd.show()
            }

            override fun deleteHabit(position: Int) {
                val deleteHabitDialog = DeleteHabitDialog(position)
                deleteHabitDialog.show(requireActivity().supportFragmentManager , "DeleteDialog")
            }
        }
        adapter = HabitAddRVAdapter(manageViewModel.HabitList)
        adapter.setListener(listener)
        binding.habitAddHabitListRv.adapter = adapter
    }

}
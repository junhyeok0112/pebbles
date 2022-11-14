package com.todaypebble.pebbles.view.manage.fragment

import android.app.DatePickerDialog
import android.icu.util.LocaleData
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bit.kodari.Config.BaseFragment
import com.google.android.material.textfield.TextInputEditText
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.FragmentStoneNameAndDayBinding
import com.todaypebble.pebbles.view.manage.ManageViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class StoneNameAndDayFragment : BaseFragment<FragmentStoneNameAndDayBinding>(R.layout.fragment_stone_name_and_day) {

    private val manageViewModel : ManageViewModel by activityViewModels()
    //해야할 거 : 이름 , 날짜 셋팅하기 -> 날짜는 DatePciker로 -> DataBinding 쓸까 ?
    //DataBinding , DatePicker
    override fun initAfterBinding() {
        binding.vm = manageViewModel
        binding.lifecycleOwner = this

        initListener()

        binding.stoneNameDayStartdayPickerEt.text = LocalDate.now().toString()
        Log.d("ManageViewModel 시작 날짜" , "${binding.stoneNameDayStartdayPickerEt.text}")
    }

    fun initListener(){

        binding.stoneNameDayStartdayTitleTv.setOnClickListener {
            manageViewModel.showInfo()
        }

        binding.stoneNameDayStartdayPickerEt.setOnClickListener {
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
                    binding.stoneNameDayStartdayPickerEt.text = "$year-$month-$dayOfMonth"

                },
                year,
                month,
                day
            )
//           최소 날짜를 현재 시각 이후로
            dpd.datePicker.minDate = System.currentTimeMillis() - 1000;
            dpd.show()
        }

        binding.stoneNameDayEnddayPickerEt.setOnClickListener {
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
                    binding.stoneNameDayEnddayPickerEt.text = "$year-$month-$dayOfMonth"

                },
                year,
                month,
                day
            )
//           최소 날짜를 현재 시각 이후로
            dpd.datePicker.minDate = System.currentTimeMillis() - 1000;
            dpd.show()
        }

        binding.stoneNameDayNameInputEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                manageViewModel.stoneName.value = binding.stoneNameDayNameInputEt.text.toString()
                Log.d("이름 변경" , "${manageViewModel.stoneName.value}")
            }
        })
    }


}
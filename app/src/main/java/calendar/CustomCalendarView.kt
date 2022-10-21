package calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import calendar.adapter.CalendarAdapter
import com.example.pebbles.R
import com.example.pebbles.databinding.CustomViewCalendarBinding
import com.example.pebbles.util.DateUtil
import java.time.LocalDate
import java.time.YearMonth
import java.util.*

class CustomCalendarView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private val binding: CustomViewCalendarBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.custom_view_calendar,
        this,
        true
    )

    private var selectedDate = LocalDate.now()


    init {

        //화면 설정
        setMonthView()

        //화살표 눌렀을 때 이벤트
        initListener()
    }

    //화면 설정
    private fun setMonthView() {

        //년월 텍스트 뷰 셋팅
        binding.customViewCalendarTitleTv.setText(DateUtil.monthYearFromDate(selectedDate))

        //해당 월 날짜 가져오기
        val dayList = daysInMonthArray(selectedDate)

        //어댑테 데이터 적용
        val adapter = CalendarAdapter(dayList)

        //레이아웃 설정하기 (열 7개)
        val manager = GridLayoutManager(context , 7)

        //레이아웃 적용
        binding.customViewCalendarRv.layoutManager = manager

        //어댑터 적용
        binding.customViewCalendarRv.adapter = adapter
    }

    private fun initListener() {
        binding.customViewCalendarPrevIv.setOnClickListener {

            //월 -1 하기기
            selectedDate = selectedDate.minusMonths(1)
            setMonthView()
        }

        binding.customViewCalendarNextIv.setOnClickListener {
            //월 +1 하기
            selectedDate = selectedDate.plusMonths(1)
            setMonthView()
        }
    }

    //날짜 생성하기
    private fun daysInMonthArray(date : LocalDate) : ArrayList<String>{
        val dayList = ArrayList<String>()

        val yearMonth = YearMonth.from(date)

        //해당 월 마지막 날짜 가져오기 (ex> 28,30,31)
        val lastDay = yearMonth.lengthOfMonth()

        //해당 월 첫 번째 날 가져오기기(ex> 4월 1일)
        val firstDay = selectedDate.withDayOfMonth(1)

        //첫 번째 날 요일 가져오기 ( 월 : 1 , 일 : 7)
        val dayOfWeek = firstDay.dayOfWeek.value

        //날짜 생성
        for(i in 1 .. 41){
            if( i <= dayOfWeek || i > lastDay + dayOfWeek ){
                dayList.add("")
            } else{
                dayList.add((i - dayOfWeek).toString())
            }
        }
        return dayList

    }

}
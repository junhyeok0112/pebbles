package calendar

import android.content.Context
import android.util.AttributeSet
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.GridLayoutManager
import calendar.adapter.CalendarAdapter
import calendar.data.Day
import com.example.pebbles.MyApplicationClass
import com.example.pebbles.R
import com.example.pebbles.databinding.CustomViewCalendarBinding
import com.example.pebbles.util.DateUtil
import com.example.pebbles.view.home.HomeViewModel
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream
import java.time.LocalDate
import java.time.YearMonth

class CustomCalendarView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private val binding: CustomViewCalendarBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.custom_view_calendar,
        this,
        true
    )

    private var selectedDate = LocalDate.now()
    private lateinit var listener: CalendarAdapter.OnCustomItemListener

    //    private val callBackFunc: (LocalDate) -> Unit
    //라이프 싸이클
    private val viewModel by lazy {
        ViewModelProvider(ViewTreeViewModelStoreOwner.get(this)!!).get(HomeViewModel::class.java)
    }


    init {
//        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomCalendarView)
//        val callBackStr = a.getString(R.styleable.CustomCalendarView_onClickListener) ?: ""
//
//        callBackFunc = createCallBackFunc(callBackStr)

        //화살표 눌렀을 때 이벤트
        initListener()

        //화면 설정
        setWeekView()
//        a.recycle()
    }

    //월간 화면 설정
    private fun setMonthView() {

        //년월 텍스트 뷰 셋팅
        binding.customViewCalendarTitleTv.setText(DateUtil.monthYearFromDate(selectedDate))

        //해당 월 날짜 가져오기
        val dayList = daysInMonthArray(selectedDate)

        //어댑테 데이터 적용
        val adapter = CalendarAdapter(dayList)

        //클릭 리스너 설정
        adapter.setListener(listener)

        //레이아웃 설정하기 (열 7개)
        val manager = GridLayoutManager(context, 7)

        //레이아웃 적용
        binding.customViewCalendarRv.layoutManager = manager

        //어댑터 적용
        binding.customViewCalendarRv.adapter = adapter
    }

    //주간 화면 설정
    private fun setWeekView() {

        //년월 텍스트 뷰 셋팅
        binding.customViewCalendarTitleTv.setText(DateUtil.monthYearFromDate(selectedDate))

        //해당 주 날짜 가져오기
        val dayList = daysInWeekArray(selectedDate)

        //어댑테 데이터 적용
        val adapter = CalendarAdapter(dayList)

        //클릭 리스너 설정
        adapter.setListener(listener)

        //레이아웃 설정하기 (열 7개)
        val manager = GridLayoutManager(context, 7)

        //레이아웃 적용
        binding.customViewCalendarRv.layoutManager = manager

        //어댑터 적용
        binding.customViewCalendarRv.adapter = adapter
    }


    private fun initListener() {
        binding.customViewCalendarPrevIv.setOnClickListener {
            //월 달력일때
            if (binding.customViewCalendarMonthIv.visibility == VISIBLE) {
                //월 -1 하기기
                selectedDate = selectedDate.minusMonths(1)
                setMonthView()
            } else { //주 -1 하기
                selectedDate = selectedDate.minusWeeks(1)
                setWeekView()
            }

        }

        binding.customViewCalendarNextIv.setOnClickListener {
            //월 달력일때
            if (binding.customViewCalendarMonthIv.visibility == VISIBLE) {
                //월 +1 하기기
                selectedDate = selectedDate.plusMonths(1)
                setMonthView()
            } else { //주 +1 하기
                selectedDate = selectedDate.plusWeeks(1)
                setWeekView()
            }
        }

        //월간에서 버튼 눌렀을 때 주간으로 변경
        binding.customViewCalendarMonthIv.setOnClickListener {
            binding.customViewCalendarMonthIv.visibility = View.GONE
            binding.customViewCalendarDayIv.visibility = View.VISIBLE
            setWeekView()
        }

        //주간에서 버튼 눌렀을 때 월간으로 변경
        binding.customViewCalendarDayIv.setOnClickListener {
            binding.customViewCalendarMonthIv.visibility = View.VISIBLE
            binding.customViewCalendarDayIv.visibility = View.GONE
            setMonthView()      //달력도 월간으로 변경
        }

        //해당 날짜 클릭시 발생할 이벤트 리스너 정의
        listener = object : CalendarAdapter.OnCustomItemListener {
            override fun onCustomItemClick(dayText: LocalDate?) {
                //dayText는 일자만 나와있음 -> 그래서 selectedDate 이용해서 연,월 가져오고 뒤에 일자 붙혀야함
                //클릭했을 때 해당 날짜 클릭했다고 View 변경해야함
                //이 값을 ViewModel에 넘겨서 View의 리스트 변경하기
                if (dayText == null) {
                    Toast.makeText(context, "다른 날을 클릭해주세요", Toast.LENGTH_SHORT).show()
                } else {
//                    val clickDay = DateUtil.yearMonthFromDate(selectedDate) + "-"+dayText
                    Toast.makeText(context, dayText.toString(), Toast.LENGTH_SHORT).show()
                    // 그 함수 호출!
//                    callBackFunc(dayText)
                    viewModel.test(dayText)
                }

            }
        }

    }

//    private fun createCallBackFunc(callBackStr: String): (LocalDate) -> Unit {
//        val decoded = Base64.decode(callBackStr, Base64.DEFAULT)
//
//        return ObjectInputStream(ByteArrayInputStream(decoded))
//            .readObject() as ((LocalDate) -> Unit)
//    }

    //월간 달력 날짜 생성하기
    private fun daysInMonthArray(date: LocalDate): ArrayList<Day> {
        val dayList = ArrayList<Day>()

        val yearMonth = YearMonth.from(date)

        //해당 월 마지막 날짜 가져오기 (ex> 28,30,31)
        val lastDay = yearMonth.lengthOfMonth()

        //해당 월 첫 번째 날 가져오기기(ex> 4월 1일)
        val firstDay = selectedDate.withDayOfMonth(1)

        //첫 번째 날 요일 가져오기 ( 월 : 1 , 일 : 7)
        val dayOfWeek = firstDay.dayOfWeek.value

        //날짜 생성
        for (i in 1..42) {
            if (i < dayOfWeek || i >= lastDay + dayOfWeek) {
                dayList.add(Day(null, 0, false))
            } else {
                //LocalDate.of(년,월,일)
                //선택했던 날짜랑 동일하면 true로 넣어줌 -> 아니면 false
                if (LocalDate.of(selectedDate.year, selectedDate.monthValue, i - dayOfWeek + 1)
                        .equals(MyApplicationClass.clickedDate)
                ) {
                    dayList.add(
                        Day(
                            LocalDate.of(
                                selectedDate.year,
                                selectedDate.monthValue,
                                i - dayOfWeek + 1
                            ), 1, true
                        )
                    )
                } else {
                    dayList.add(
                        Day(
                            LocalDate.of(
                                selectedDate.year,
                                selectedDate.monthValue,
                                i - dayOfWeek + 1
                            ), 1, false
                        )
                    )
                }

            }
        }
        return dayList

    }

    //주간 달력 날짜 생성하기
    private fun daysInWeekArray(date: LocalDate): ArrayList<Day> {
        val dayList = ArrayList<Day>()

        val yearMonth = YearMonth.from(date)

        //해당 월 마지막 날짜 가져오기 (ex> 28,30,31) -> 이거 넘어가면 안보이게
        val lastDay = yearMonth.lengthOfMonth()

        //이전달에 몇일까지 있는지 -> startDay <= 0 일 때 사용
        val lastMonthDay = YearMonth.from(date.minusMonths(1)).lengthOfMonth()
        //몇일인지
        val monthWeek = date.dayOfMonth

        //무슨 요일인지       1 ~ 7 의 값으로
        val dayOfWeek = date.dayOfWeek.value

        //해당 주차 시작 날짜 -> 만약 0 이하면 다른 날짜 나오게 변환 처리해야함 -> 작은 경우는 무조건 저번달 일 경우임.
        val startDay = monthWeek - (dayOfWeek) + 1

        //날짜 생성 -> 해당 요일부터 -> 현재 요일 - (dayOfWeek + 1) 부터 7개
        //주간은 항상 활성화 버튼
        for (i in startDay until startDay + 7) {
            if (i > lastDay) {
                if (LocalDate.of(selectedDate.year, selectedDate.monthValue + 1, i - lastDay)
                        .equals(MyApplicationClass.clickedDate)
                ) {
                    dayList.add(
                        Day(
                            LocalDate.of(
                                selectedDate.year,
                                selectedDate.monthValue + 1,
                                i - lastDay
                            ), 1, true
                        )
                    )   //lastDay넘어가면 1일부터 표시하기.
                } else {
                    dayList.add(
                        Day(
                            LocalDate.of(
                                selectedDate.year,
                                selectedDate.monthValue + 1,
                                i - lastDay
                            ), 1, false
                        )
                    )   //lastDay넘어가면 1일부터 표시하기.
                }

            } else if (i <= 0) {  //0이하면 이전달 값 넣기
                if (LocalDate.of(selectedDate.year, selectedDate.monthValue - 1, lastMonthDay + i)
                        .equals(MyApplicationClass.clickedDate)
                ) {
                    dayList.add(
                        Day(
                            LocalDate.of(
                                selectedDate.year,
                                selectedDate.monthValue - 1,
                                lastMonthDay + i
                            ), 1, true
                        )
                    )
                } else {
                    dayList.add(
                        Day(
                            LocalDate.of(
                                selectedDate.year,
                                selectedDate.monthValue - 1,
                                lastMonthDay + i
                            ), 1, false
                        )
                    )
                }
            } else { //현재 값 표시하기.
                if (LocalDate.of(selectedDate.year, selectedDate.monthValue, i)
                        .equals(MyApplicationClass.clickedDate)
                ) {
                    dayList.add(
                        Day(
                            LocalDate.of(selectedDate.year, selectedDate.monthValue, i),
                            1,
                            true
                        )
                    )
                } else {
                    dayList.add(
                        Day(
                            LocalDate.of(selectedDate.year, selectedDate.monthValue, i),
                            1,
                            false
                        )
                    )
                }
            }
        }

        return dayList
    }


}
package com.example.pebbles.view.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pebbles.MyApplicationClass
import com.example.pebbles.data.remote.dto.Todo
import com.example.pebbles.data.remote.model.Habit
import com.example.pebbles.domain.usecase.GetHabitsFromAPIUseCase
import com.example.pebbles.domain.usecase.GetHabitsFromDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

//Application을 할당하는게 좋은 방법일까 ?
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHabitsFromAPIUseCase: GetHabitsFromAPIUseCase,
    private val getHabitsFromDBUseCase: GetHabitsFromDBUseCase
) : ViewModel() {
    //Viwjscp fewModel에서의

    //테스트해서 받아보고 init에서 없애기 -> Room와 Retrofit 둘다 사용해야함함
//    val homeRepository = HomeRepositoryImpl(PEBBLEDataBase.getDB())

    var allList = HashMap<String, ArrayList<Habit>>()
    var habitList = MutableLiveData<List<Habit>>()


    init {
        viewModelScope.launch {
            getHabitsFromAPIUseCase()
        }

        //더미데이터 -> 추후에 비동기 통신으로 값을 가져와야하는 부분
        val all: ArrayList<ArrayList<Habit>> = ArrayList()
        val habits1: ArrayList<Habit> = ArrayList()
        val habits2: ArrayList<Habit> = ArrayList()
        val habits3: ArrayList<Habit> = ArrayList()
        val todos1: ArrayList<Todo> = ArrayList()
        todos1.add(Todo(0, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "1"))
        todos1.add(Todo(1, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "1"))
        val todos2: ArrayList<Todo> = ArrayList()
        todos2.add(Todo(0, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "0"))
        todos2.add(Todo(1, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "1"))
        todos2.add(Todo(2, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "0"))
        val todos3: ArrayList<Todo> = ArrayList()
        todos3.add(Todo(0, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "1"))
        todos3.add(Todo(1, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "0"))
        todos3.add(Todo(2, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "1"))
        todos3.add(Todo(3, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "0"))
        val todos4: ArrayList<Todo> = ArrayList()
        todos4.add(Todo(0, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "1"))
        todos4.add(Todo(1, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "1"))
        val todos5: ArrayList<Todo> = ArrayList()
        todos5.add(Todo(0, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "0"))
        todos5.add(Todo(1, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "1"))
        todos5.add(Todo(2, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "0"))
        val todos6: ArrayList<Todo> = ArrayList()
        todos6.add(Todo(0, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "1"))
        todos6.add(Todo(1, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "0"))
        todos6.add(Todo(2, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "1"))
        todos6.add(Todo(3, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "0"))
        val todos7: ArrayList<Todo> = ArrayList()
        todos7.add(Todo(0, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "1"))
        todos7.add(Todo(1, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "1"))
        val todos8: ArrayList<Todo> = ArrayList()
        todos8.add(Todo(0, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "0"))
        todos8.add(Todo(1, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "1"))
        todos8.add(Todo(2, "투두 내용은 아마 내용이 길겠지요 ? ", 0, "0"))
        habits1.add(
            Habit(
                0,
                "2022-10-27",
                1,
                "해빗 1에 대한 내용",
                0,
                "2022-10-11",
                "1",
                "10:22",
                "2022-10-30",
                "1",
                todos1,
                "0"
            )
        )
        habits1.add(
            Habit(
                0,
                "2022-10-27",
                2,
                "해빗 2에 대한 내용",
                0,
                "2022-10-11",
                "0",
                "10:22",
                "2022-10-30",
                "0",
                todos2,
                "0"
            )
        )
        habits1.add(
            Habit(
                0,
                "2022-10-27",
                3,
                "해빗 3에 대한 내용",
                0,
                "2022-10-11",
                "0",
                "10:22",
                "2022-10-30",
                "0",
                todos3,
                "0"
            )
        )
        habits2.add(
            Habit(
                0,
                "2022-10-28",
                1,
                "해빗 4에 대한 내용",
                0,
                "2022-10-11",
                "1",
                "10:22",
                "2022-11-01",
                "1",
                todos4,
                "0"
            )
        )
        habits2.add(
            Habit(
                0,
                "2022-10-28",
                2,
                "해빗 5에 대한 내용",
                0,
                "2022-10-11",
                "0",
                "10:22",
                "2022-11-01",
                "0",
                todos5,
                "0"
            )
        )
        habits2.add(
            Habit(
                0,
                "2022-10-28",
                3,
                "해빗 6에 대한 내용",
                0,
                "2022-10-11",
                "0",
                "10:22",
                "2022-11-01",
                "0",
                todos6,
                "0"
            )
        )
        habits3.add(
            Habit(
                0,
                "2022-10-28",
                3,
                "해빗 6에 대한 내용",
                0,
                "2022-10-11",
                "0",
                "10:22",
                "2022-10-31",
                "0",
                todos7,
                "0"
            )
        )

        //온 Habit 리스트들로 셋팅하기.
        all.add(habits1)
        all.add(habits2)
        all.add(habits3)
        for (cur in all) {    //cur은 해당 날짜의 Habit List
            //어짜피 날짜별로 오기 때문에 하나만 봐도 될듯
            val today = cur[0].today
            Log.d("LocalDate_today", today)
            if (allList.containsKey(today)) {
                //포함하고 있으면 해당 날짜에 Habit들을 리스트에 추가하기 -> cur이 리스트
                for (temp in cur) {
                    //temp가 Habit들
                    if (today < LocalDate.now().toString()) { //과거의 것이면 todoList 필요없음
                        temp.todos.clear()
                        allList.get(today)?.add(temp)
                    } else {                                 //오늘 ~ 미래거면 todo 필요 -> 미래는 Todo를 보여주나 ?
                        allList.get(today)?.add(temp)
                    }
                }
            } else {
                if (today < LocalDate.now().toString()) {
                    for (temp in cur) {
                        temp.todos.clear()
                    }
                }
                allList.put(today, cur)
            }
        }

        //초기값 셋팅을 어떻게 할까?
        Log.d("LocalDate", LocalDate.now().toString())
        habitList.value = allList.get(MyApplicationClass.clickedDate.toString())
    }

    fun setHabitList(recyclerViewHabits: ArrayList<Habit>) {
        habitList.value = recyclerViewHabits
    }

    //여기서 비지니스 로직 처리
    //1. 해당 모래알 클릭하면 바뀌게 하기 몇 번째 Habbit의 몇번째 todo에 있는 아이템인지
    fun changeTodo(item: Todo, habit_id: Int) {
        //해당 item을 리스트에서 찾아서 변경해주어야함 , 그리고 Habit내의 Todo가 전부 1로 활성화 되면 파란색으로 바꿔야함.
        Log.d("HomeViewModel", "${habit_id}  ${item.id}")
        val temp = ArrayList<Habit>()
        habitList.value!!.forEachIndexed { index, habit ->
            if (habit.id == habit_id) {
                var cnt = 0
                for (todo in habit.todos) {
                    if (todo.id == item.id) {
                        if (todo.status == "0") todo.status = "1"
                        else todo.status = "0"
                        Log.d("HomeViewModel", todo.status)
                    }
                    if (todo.status == "1") cnt++
                }
                //전부 1로 바뀌었으면
                if (cnt == habit.todos.size) {
                    habit.today_status = "1"
                } else {
                    habit.today_status = "0"
                }
            }

            temp.add(
                Habit(
                    habit.cons_days,
                    habit.end,
                    habit.id,
                    habit.name,
                    habit.seq,
                    habit.start,
                    habit.status,
                    habit.time,
                    habit.today,
                    habit.today_status,
                    habit.todos,
                    habit.week
                )
            )
        }
        //바꾸고 Hash 값에도 갱신해줘야함 , 바꾸는건 일단 당일만 가능 -> 하지만 ? 지금은 테스트 중이니..
        allList.put(MyApplicationClass.clickedDate.toString(), temp)
        setHabitList(temp)
    }

    fun test(date: LocalDate) {
        Log.d("Test", "HomeViewModel 데이터 출력 ${date}")
        //리스트 날짜에 맞게 변경하기
        if (allList.containsKey(date.toString())) {
            habitList.value = allList.get(date.toString())
        } else {     //비어있는 리스트로 셋팅
            habitList.value = ArrayList()
        }

    }
}
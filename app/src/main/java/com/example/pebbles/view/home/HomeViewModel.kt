package com.example.pebbles.view.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pebbles.MyApplicationClass
import com.example.pebbles.data.remote.dto.Todo
import com.example.pebbles.data.remote.dto.logout.WithdrawalResponse
import com.example.pebbles.data.remote.dto.update.HomeUpdateRequest
import com.example.pebbles.data.remote.dto.update.HomeUpdateRequestItem
import com.example.pebbles.data.remote.model.Habit
import com.example.pebbles.domain.usecase.*
import com.example.pebbles.domain.usecase.logout.WithdrawalUseCase
import com.example.pebbles.util.getUserIdx
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate
import javax.inject.Inject

//Application을 할당하는게 좋은 방법일까 ?
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHabitsFromAPIUseCase: GetHabitsFromAPIUseCase,
    private val getHabitsFromDBUseCase: GetHabitsFromDBUseCase,
    private val getTodayFromDBUseCase: GetTodayFromDBUseCase,
    private val updateTodoToDBUseCase: UpdateTodoToDBUseCase,
    private val updateHabitToAPIUseCase: UpdateHabitsToAPIUseCase,
    private val withdrawalUseCase: WithdrawalUseCase
) : ViewModel() {
    //Viwjscp fewModel에서의

    //테스트해서 받아보고 init에서 없애기 -> Room와 Retrofit 둘다 사용해야함함
//    val homeRepository = HomeRepositoryImpl(PEBBLEDataBase.getDB())

    var allList = HashMap<String, ArrayList<Habit>>()
    var habitList = MutableLiveData<List<Habit>>()


    init {
        var temp: ArrayList<Habit>? = ArrayList()

        viewModelScope.async {
            val list = async { getHabitsFromAPIUseCase() }
            temp = list.await()
            Log.d("ViewModel2", "${temp}")

            for (cur in temp!!) {    //cur은 해당 날짜의 Habit List
                //어짜피 날짜별로 오기 때문에 하나만 봐도 될듯
                val today = cur.today
                Log.d("LocalDate_today", today)
                if (allList.containsKey(today)) {
                    //포함하고 있으면 해당 날짜에 Habit들을 리스트에 추가하기 -> cur이 Habit
                    //temp가 Habit들
                    if (today < LocalDate.now().toString()) { //과거의 것이면 todoList 필요없음
                        cur.todos = listOf()
                        allList.get(today)?.add(cur)
                    } else {                                 //오늘 ~ 미래거면 todo 필요 -> 미래는 Todo를 보여주나 ?
                        allList.get(today)?.add(cur)
                    }

                } else {
                    if (today < LocalDate.now().toString()) {
                        cur.todos = listOf()
                    }
                    val temp_list = ArrayList<Habit>()      //새로운 Habit 리스트 만들어서 할당
                    temp_list.add(cur)
                    allList.put(today,temp_list)
                }

            }
            //오늘 날짜꺼는 RoomDB에 있는걸로 해야함 -> 보면 어짜피 getHabitFromDB 실행해야함.
            //날짜가 오늘꺼가 아니거나 비었으면 getHabitFromDB로 갱신 후 리스트 가져옴
            //만약 날짜가 오늘꺼면 getHabitFromDB로 지금 데이터 가져옴.
            //getHabitFromDB로 오늘 날짜에 해당하는 값 다시 셋팅
            allList.put(LocalDate.now().toString() , getHabitsFromDBUseCase() as ArrayList<Habit>)



            //초기값 셋팅을 어떻게 할까?
            Log.d("LocalDate", LocalDate.now().toString())
            habitList.value = allList.get(MyApplicationClass.clickedDate.toString())
            Log.d("ViewModel2", "셋팅 끝")
        }

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
                        if (todo.status == "False") todo.status = "True"
                        else todo.status = "False"
                        Log.d("HomeViewModel", todo.status)
                    }
                    if (todo.status == "True") cnt++
                }
                //전부 1로 바뀌었으면
                if (cnt == habit.todos.size) {
                    habit.today_status = "True"
                } else {
                    habit.today_status = "False"
                }
                //habit.todos가 갱신되었을 꺼임 -> 그러면 이거를 바꾸면 되지 않을까 ?
                //habit_id로 해당 habit찾고 , habit_todos를 갱신하기.->이때 저장은 json인데 그냥 그 json자체를 바꿔줌
                CoroutineScope(Dispatchers.IO).launch {
                    updateTodoToDBUseCase(habit)
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
                    habit.today,
                    habit.today_status,
                    habit.todos,
                    habit.weeks
                )
            )
        }
        //바꾸고 Hash 값에도 갱신해줘야함 , 바꾸는건 일단 당일만 가능 -> 하지만 ? 지금은 테스트 중이니..
        allList.put(MyApplicationClass.clickedDate.toString(), temp)
        setHabitList(temp)
    }
//날짜 변경하기
    fun changeDay(date: LocalDate) {

        //리스트 날짜에 맞게 변경하기
        if (allList.containsKey(date.toString())) {
            habitList.value = allList.get(date.toString())
        } else {     //비어있는 리스트로 셋팅
            habitList.value = ArrayList()
        }
    }

    //오늘의 Habits 정보들 서버로 보내기
    fun updateHabits(){
        viewModelScope.launch {
            val list = getHabitsFromDBUseCase() //호출해서 받아오고
            val homeUpdateRequest = HomeUpdateRequest()
            if (list != null) {
                for(cur in list){
                    homeUpdateRequest.add(HomeUpdateRequestItem(cur.id, cur.today_status,cur.today))    //Habit리스트 셋팅
                }
                Log.d("UpdateTest" , "${updateHabitToAPIUseCase(homeUpdateRequest).body()}")
            }
        }
    }

    //로그 아웃 하기
    suspend fun withdrawal(userId:Int) : Response<WithdrawalResponse>  = withdrawalUseCase(userId)

}
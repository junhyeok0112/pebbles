package com.todaypebble.pebbles.view.manage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.todaypebble.pebbles.data.remote.dto.manage.*
import com.todaypebble.pebbles.domain.usecase.manage.GetDetailMyStoneUseCase
import com.todaypebble.pebbles.domain.usecase.manage.GetMyStonesUseCase
import com.todaypebble.pebbles.domain.usecase.manage.PostMakeStoneUseCase
import com.todaypebble.pebbles.util.getUserIdx
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ManageViewModel @Inject constructor(
    private val getMyStonesUseCase: GetMyStonesUseCase,
    private val postMakeStoneUseCase : PostMakeStoneUseCase,
    private val getDetailMyStoneUseCase: GetDetailMyStoneUseCase
) : ViewModel() {

    var stoneList = MutableLiveData<List<MyStone>?>()
//하이라이트 정보
    var stoneName = MutableLiveData<String>().apply { value = "" }
    var stoneStartDay = MutableLiveData<String>().apply {
        Log.d("ManageViewModel" , "스타트 apply")
        value = LocalDate.now().toString() }
    var stoneEndDay = MutableLiveData<String>().apply { value = "" }
    //내 돌탑 상세 정보
    var detailStone = DetailMyStoneResult()
    //Habit 리스트
    var HabitList = MutableLiveData<ArrayList<Habit>>().apply { value = ArrayList() }
    init {
        viewModelScope.async {
            updateStoneList()
        }
    }

    fun addHabit(){
        var temp_todo = ArrayList<Todo>()
        temp_todo.add(Todo("",0))
        temp_todo.add(Todo("",1))
        temp_todo.add(Todo("",2))
        HabitList.value?.add(Habit(ArrayList() , stoneEndDay.value!!,"",HabitList.value!!.size+1,stoneStartDay.value!!,temp_todo,Weeks(false,false,false,false,false,false,false)))
        Log.d("ManageViewModel" , "addHabit() 실행 , ${HabitList.value?.size}")
    }


    fun initStoneInfo(){
        stoneName.value = ""
        stoneStartDay.value = ""
        stoneEndDay.value = ""
        HabitList.value = ArrayList()
        detailStone = DetailMyStoneResult()
    }
//days 계산하면서 새로운 하이라이트 만들기. Todo 리스트들 계산해서 공백인거 제거하고, 전부 공백이면 Habit 이름이랑 동일하게 만들기
    suspend fun makeNewStone() : MakeStoneResponse{
        //Habit의 days 값까지 가공해야함 -> 요일까지
        for(cur in HabitList.value!!){
            //cur.days에 start  ~ end 까지 해당하는 날짜들 넣기
            //무슨 요일들이 필요한지 넣어두자 , 월 : 1 일 : 7
            var hs = HashSet<Int>()
            if(cur.weeks.mon) hs.add(1)
            if(cur.weeks.tue) hs.add(2)
            if(cur.weeks.wed) hs.add(3)
            if(cur.weeks.thu) hs.add(4)
            if(cur.weeks.fri) hs.add(5)
            if(cur.weeks.sat) hs.add(6)
            if(cur.weeks.sun) hs.add(7)
            var start_date  = LocalDate.parse(cur.start , DateTimeFormatter.ISO_DATE)
            var end_date = LocalDate.parse(cur.end , DateTimeFormatter.ISO_DATE)
            while(start_date <= end_date){
                Log.d("테스트" , "while 시작")
                var temp = start_date.dayOfWeek.value
                if(hs.contains(temp)) cur.days.add(start_date.toString())
                start_date = start_date.plusDays(1)
            }


            //Todo 계산.
            //Todo 계산하려면 -> Habit애들 중 todo가 비어있는 것들은 삭제 , 단 다 삭제 후 todo size 가 0이면 Habit이름의 todo 추가
            val remove_todo = ArrayList<Todo>()
            for(todo in cur.todos){
                if(todo.name == ""){    //값 없으면 해당꺼 삭제
                    remove_todo.add(todo)
                }
            }

            for(remove in remove_todo){
                cur.todos.remove(remove)
            }
            //만약 todo가 전부 삭제됐으면 Habit 이름의 todo 추가
            if(cur.todos.size == 0){
                cur.todos.add(Todo(cur.name , 0))   //0번으로
            }
        }

        Log.d("테스트" , "${HabitList.value}")

        //보내기전 마지막 점검
        showInfo()

        //완료한뒤 통신하기 -> 통신 성공하면 viewModel 리셋, 결과 화면으로 넘어가기.
        return postMakeStoneUseCase(getUserIdx() , MakeStoneRequest(stoneEndDay.value!! , HabitList.value!! , stoneName.value!! , stoneStartDay.value!! ))
    }

    //현재 manageViewModel의 값들 출력
    fun showInfo(){
        Log.d("ManageViewModel" , "이름 : ${stoneName.value}")
        Log.d("ManageViewModel" , "시작 날짜 : ${stoneStartDay.value}")
        Log.d("ManageViewModel" , "종료 날짜 : ${stoneEndDay.value}")
        Log.d("ManageViewModel" , "리스트 : ${HabitList.value}")

    }

    //Habit 삭제 버튼 눌렀을 시
    fun deleteHabit(position : Int){
        HabitList.value!!.removeAt(position)
    }

    //Todo 추가 눌렀을 시
    fun addTodo(position : Int){
        HabitList.value?.get(position)?.todos?.add(Todo("",HabitList.value?.get(position)?.todos?.size!!))
    }

    //Todo 이름 변경
    fun writeTodo(position: Int , habitPosition : Int , name :String){
        HabitList.value?.get(habitPosition)?.todos?.get(position)?.name = name
    }

    fun deleteTodo(position: Int , habitPosition : Int){
        HabitList.value?.get(habitPosition)?.todos?.removeAt(position)
    }

    suspend fun updateStoneList(){
        stoneList.value = getMyStonesUseCase(getUserIdx())
    }

    //내가 클릭한 바윗돌 세부정보 가져오기 -> 셋팅하기
    suspend fun getDetailMyStone(userId: Int, highlightId: Int){
        detailStone = getDetailMyStoneUseCase(userId, highlightId)
    }
}
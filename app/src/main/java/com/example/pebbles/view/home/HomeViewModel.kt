package com.example.pebbles.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pebbles.data.remote.dto.Todo
import com.example.pebbles.data.remote.model.Habit

class HomeViewModel : ViewModel() {
    //ViewModel에서의
    private val _habbitList = MutableLiveData<List<Habit>>()
    val habit_data = ArrayList<Habit>()                    //이 데이터로 _habbitList에 추가하면서 갱신해주어야함
    val habitList : LiveData<List<Habit>> get() = _habbitList

    init{
        //더미데이터 -> 추후에 비동기 통신으로 값을 가져와야하는 부분
        val recyclerViewHabits : ArrayList<Habit> = ArrayList()
        val todos1 : ArrayList<Todo> = ArrayList()
        todos1.add(Todo(0,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"0"))
        todos1.add(Todo(0,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"1"))
        val todos2 : ArrayList<Todo> = ArrayList()
        todos2.add(Todo(0,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"0"))
        todos2.add(Todo(0,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"1"))
        todos2.add(Todo(0,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"0"))
        val todos3 : ArrayList<Todo> = ArrayList()
        todos3.add(Todo(0,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"1"))
        todos3.add(Todo(0,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"0"))
        todos3.add(Todo(0,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"1"))
        todos3.add(Todo(0,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"0"))
        recyclerViewHabits.add(Habit(0,"2022-11-11" ,1,"해빗 1에 대한 내용",0,"2022-10-11","0","10:22","2022-10-11","0",todos1,"0"))
        recyclerViewHabits.add(Habit(0,"2022-11-11" ,1,"해빗 2에 대한 내용",0,"2022-10-11","0","10:22","2022-10-11","0",todos2,"0"))
        recyclerViewHabits.add(Habit(0,"2022-11-11" ,1,"해빗 3에 대한 내용",0,"2022-10-11","0","10:22","2022-10-11","0",todos3,"0"))
        Log.d("HomeView" , "실행")
        _habbitList.value = recyclerViewHabits
    }

    fun setHabbitList(recyclerViewHabits:ArrayList<Habit>){
        _habbitList.value = recyclerViewHabits
    }

    //여기서 비지니스 로직 처리
    //1. 해당 모래알 클릭하면 바뀌게 하기 몇 번째 Habbit의 몇번째 todo에 있는 아이템인지
    fun changeTodo(habbit_position : Int, todo_position : Int){
        if(_habbitList.value?.get(habbit_position)?.todos?.get(todo_position)?.status == "0"){
            _habbitList.value?.get(habbit_position)?.todos?.get(todo_position)?.status = "1"
        } else{
            _habbitList.value?.get(habbit_position)?.todos?.get(todo_position)?.status = "0"
        }

    }
}
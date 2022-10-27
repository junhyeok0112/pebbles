package com.example.pebbles.view.home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pebbles.data.remote.dto.Todo
import com.example.pebbles.data.remote.model.Habit

class HomeViewModel : ViewModel() {
    //Viwjscp fewModel에서의
    var habitList = MutableLiveData<List<Habit>>()
    var habit_data = ArrayList<Habit>()                    //이 데이터로 _habbitList에 추가하면서 갱신해주어야함


    init{
        //더미데이터 -> 추후에 비동기 통신으로 값을 가져와야하는 부분
        val recyclerViewHabits : ArrayList<Habit> = ArrayList()
        val todos1 : ArrayList<Todo> = ArrayList()
        todos1.add(Todo(0,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"0"))
        todos1.add(Todo(1,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"1"))
        val todos2 : ArrayList<Todo> = ArrayList()
        todos2.add(Todo(0,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"0"))
        todos2.add(Todo(1,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"1"))
        todos2.add(Todo(2,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"0"))
        val todos3 : ArrayList<Todo> = ArrayList()
        todos3.add(Todo(0,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"1"))
        todos3.add(Todo(1,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"0"))
        todos3.add(Todo(2,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"1"))
        todos3.add(Todo(3,"투두 내용은 아마 내용이 길겠지요 ? " , 0,"0"))
        recyclerViewHabits.add(Habit(0,"2022-11-11" ,1,"해빗 1에 대한 내용",0,"2022-10-11","0","10:22","2022-10-11","0",todos1,"0"))
        recyclerViewHabits.add(Habit(0,"2022-11-11" ,2,"해빗 2에 대한 내용",0,"2022-10-11","0","10:22","2022-10-11","0",todos2,"0"))
        recyclerViewHabits.add(Habit(0,"2022-11-11" ,3,"해빗 3에 대한 내용",0,"2022-10-11","0","10:22","2022-10-11","0",todos3,"0"))
        habit_data = recyclerViewHabits
        habitList.value = recyclerViewHabits
    }

    fun setHabitList(recyclerViewHabits:ArrayList<Habit>){
        habitList.value = recyclerViewHabits
    }

    //여기서 비지니스 로직 처리
    //1. 해당 모래알 클릭하면 바뀌게 하기 몇 번째 Habbit의 몇번째 todo에 있는 아이템인지
    fun changeTodo(item : Todo , habit_id : Int){
        //해당 item을 리스트에서 찾아서 변경해주어야함 , 그리고 Habit내의 Todo가 전부 1로 활성화 되면 파란색으로 바꿔야함.
        Log.d("HomeViewModel" , "${habit_id}  ${item.id}")
        val temp = ArrayList<Habit>()
        habitList.value!!.forEachIndexed { index, habit ->
            if(habit.id == habit_id){
                var cnt = 0
                for(todo in habit.todos){
                    if(todo.id == item.id){
                        if(todo.status == "0") todo.status ="1"
                        else todo.status ="0"
                        Log.d("HomeViewModel" , todo.status)
                    }
                    if(todo.status == "1") cnt++
                }
                //전부 1로 바뀌었으면
                if(cnt == habit.todos.size){
                    habit.today_status = "1"
                } else{
                    habit.today_status = "0"
                }
            }

            temp.add(Habit(habit.cons_days , habit.end, habit.id , habit.name,habit.seq , habit.start , habit.status , habit.time , habit.today , habit.today_status , habit.todos ,habit.week))
        }

        setHabitList(temp)
    }
}
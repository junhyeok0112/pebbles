package com.example.pebbles.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pebbles.data.remote.dto.Todo
import com.example.pebbles.databinding.ItemTodoHomeBinding
import com.example.pebbles.view.home.adapter.viewholder.TodoVeiwHolder

class TodoRVAdapter(private var todos : ArrayList<Todo> , var habit_id :Int) : RecyclerView.Adapter<TodoVeiwHolder>() {

    private lateinit var listener: HabitRVAdapter.TodoButtonListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoVeiwHolder {
        val binding = ItemTodoHomeBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return TodoVeiwHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoVeiwHolder, position: Int) {
        todos.get(position).let {
            holder.bind(it)
        }
        //Sand 버튼 눌렀을 때 해당 아이템을 리스너 매개변수에 전달 -> 이후 Fragment에서 처리
        holder.getBinding().itemTodoChkIv.setOnClickListener { listener.onSandClick(todos.get(position) , habit_id) }

    }

    override fun getItemCount(): Int = todos.size

    fun setListener(listener: HabitRVAdapter.TodoButtonListener) {
        this.listener = listener
    }
}
package com.todaypebble.pebbles.view.manage.adapter.viewholder

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.manage.Todo
import com.todaypebble.pebbles.databinding.ItemSandAddBinding
import com.todaypebble.pebbles.databinding.ItemSandTodoBinding
import com.todaypebble.pebbles.view.manage.adapter.SandAddRVAdapter

class SandTodoViewHolder(private val binding : ItemSandTodoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item : Todo , listener: SandAddRVAdapter.clickListener , habitPosition : Int){
        //입력 끝났을 때 ViewModel에 있는거에 반영하기
        binding.itemSandTodoEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("SandTOdoviewHodler" , "${adapterPosition}  ${habitPosition}")
                listener.writeTodo(adapterPosition , habitPosition , binding.itemSandTodoEt.text.toString())
            }
        })
    }
}
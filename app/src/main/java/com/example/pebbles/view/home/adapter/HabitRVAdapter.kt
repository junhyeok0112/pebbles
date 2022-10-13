package com.example.pebbles.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.pebbles.data.remote.model.Habit
import com.example.pebbles.databinding.ItemHabitHomeBinding
import com.example.pebbles.view.home.adapter.viewholder.HabitViewHolder

class HabitRVAdapter(private var habbits : LiveData<ArrayList<Habit>>): RecyclerView.Adapter<HabitViewHolder>() , onItemSelectedInterface {

    private lateinit var mLister : onItemSelectedInterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ItemHabitHomeBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        //let 함수를 쓰면 객체의 상태를 변경할 수 있다.
        //또한 non-null이므로 null체크에도 용이
        habbits.value?.get(position)?.let {      //해당값이 null이 아니면
            holder.bind(it)
        }

    }

    override fun getItemCount(): Int = habbits.value?.size!!

    override fun onItemSelected(v: View, position: Int) {

    }
}
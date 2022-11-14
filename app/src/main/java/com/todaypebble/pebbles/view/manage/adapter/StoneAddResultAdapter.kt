package com.todaypebble.pebbles.view.manage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.todaypebble.pebbles.data.remote.dto.manage.Habit
import com.todaypebble.pebbles.databinding.ItemResultBinding
import com.todaypebble.pebbles.databinding.ItemSandTodoBinding
import com.todaypebble.pebbles.view.manage.adapter.viewholder.SandTodoViewHolder
import com.todaypebble.pebbles.view.manage.adapter.viewholder.StoneAddResultViewHolder

class StoneAddResultAdapter(private var habits: ArrayList<Habit>) : RecyclerView.Adapter<StoneAddResultViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoneAddResultViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoneAddResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoneAddResultViewHolder, position: Int) {
        habits.get(position).let {      //해당값이 null이 아니면
            //Bind 하면서 해당 listener도 넘겨줌
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = habits.size
}
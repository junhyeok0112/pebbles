package calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pebbles.databinding.CustomCalendarCellBinding

class CalendarAdapter(var dayList :ArrayList<String>) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = CustomCalendarCellBinding.inflate(LayoutInflater.from(parent.context) , parent ,false)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(dayList[position])
    }

    override fun getItemCount(): Int = dayList.size

    inner class CalendarViewHolder(val binding: CustomCalendarCellBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(day : String){
            binding.day = day
        }
    }
}
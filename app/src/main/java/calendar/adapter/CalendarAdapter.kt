package calendar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import calendar.data.Day
import com.example.pebbles.R
import com.example.pebbles.databinding.CustomCalendarCellBinding

class CalendarAdapter(var dayList :ArrayList<Day>) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>(){

    private lateinit var listener: OnCustomItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = CustomCalendarCellBinding.inflate(LayoutInflater.from(parent.context) , parent ,false)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        //받아온 날짜 셋팅
        val day = dayList[position]
        holder.bind(day)
        holder.binding.root.setOnClickListener {
            //인터페이스를 통해 해당 날짜 넘기기
            listener.onCustomItemClick(day.num)
            holder.binding.root.setBackgroundResource(R.drawable.custom_day_cell_selected)
            holder.binding.customCellDayTextTv.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.gray_50))
        }
    }

    override fun getItemCount(): Int = dayList.size

    inner class CalendarViewHolder(val binding: CustomCalendarCellBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(day : Day){
            binding.day = day
        }
    }

    //리스너 셋티아
    fun setListener(listener: OnCustomItemListener){
        this.listener = listener
    }


    interface OnCustomItemListener {
        fun onCustomItemClick(dayText : String)
    }
}
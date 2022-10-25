package calendar.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import calendar.data.Day
import com.example.pebbles.MyApplicationClass
import com.example.pebbles.R
import com.example.pebbles.databinding.CustomCalendarCellBinding
import java.time.LocalDate

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

            //날짜를 눌렀을 경우
            if(day.num != null){
                for(i in 0..dayList.size-1 ){
                    if(dayList[i].num == MyApplicationClass.clickedDate){
                        //해당 아이템 View 변경.. 흠..
                        notifyItemChanged(i)        //해당 지점 다시 그려서 선택 안되게 그림
                    }
                }
                MyApplicationClass.clickedDate = day.num
                Log.d("Test" , "${MyApplicationClass.clickedDate}")
                holder.binding.root.setBackgroundResource(R.drawable.custom_day_cell_selected)
                holder.binding.customCellDayTextTv.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.gray_50))
            }

        }
    }

    override fun getItemCount(): Int = dayList.size

    inner class CalendarViewHolder(val binding: CustomCalendarCellBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(day : Day){
            binding.day = day
        }
    }

    //리스너 셋팅
    fun setListener(listener: OnCustomItemListener){
        this.listener = listener
    }


    interface OnCustomItemListener {
        fun onCustomItemClick(dayText : LocalDate?)
    }
}
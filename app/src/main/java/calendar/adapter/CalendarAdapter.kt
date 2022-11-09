package calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import calendar.data.Day
import com.todaypebble.pebbles.MyApplicationClass
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.CustomCalendarCellBinding
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
                        //해당 아이템 View 변경.. 흠.. 새로 해당 아이템 추가
                        dayList[i].isSelected = false
                        notifyItemChanged(i)
                    }
                }
                MyApplicationClass.clickedDate = day.num
                dayList[position].isSelected = true
                holder.binding.root.setBackgroundResource(R.drawable.custom_day_cell_selected)
                holder.binding.customCellDayTextTv.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.gray_50))
            }

        }
    }

    override fun getItemCount(): Int = dayList.size

    inner class CalendarViewHolder(val binding: CustomCalendarCellBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(day : Day){
            binding.day = day

            //ClickDate이면 체크 표시 , 아니면 체크 해제 표시
            if(day.isSelected){
                binding.root.setBackgroundResource(R.drawable.custom_day_cell_selected)
                binding.customCellDayTextTv.setTextColor(ContextCompat.getColor(binding.root.context, R.color.gray_50))
            } else{ //아니면
                binding.root.setBackgroundResource(R.drawable.custom_day_cell_unselected)
                binding.customCellDayTextTv.setTextColor(ContextCompat.getColor(binding.root.context, R.color.gray_30))
            }

            //state == 0 이면 Blank로 처리하기
            if(day.state == 0){
                binding.root.setBackgroundResource(R.drawable.custom_day_cell_blank)
            }

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
package calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.pebbles.R
import com.example.pebbles.databinding.CustomViewCalendarBinding

class CustomCalendarView(context: Context , attrs:AttributeSet) : ConstraintLayout(context, attrs) {
    private val binding:CustomViewCalendarBinding = DataBindingUtil.inflate(LayoutInflater.from(context) , R.layout.custom_view_calendar , this, true)
}
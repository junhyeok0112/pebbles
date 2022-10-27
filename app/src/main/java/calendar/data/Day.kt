package calendar.data

import java.time.LocalDate

data class Day (
    var num : LocalDate?,
    var state : Int,     //해당 값에 따라 보이는 이미지 다름
    var isSelected : Boolean
)
package com.todaypebble.pebbles.view.manage

import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.bit.kodari.Config.BaseDialogFragment
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.DialogHabitDeleteBinding

class DeleteTodoDialog(private val position : Int , private val habitPosition : Int) : BaseDialogFragment<DialogHabitDeleteBinding>(R.layout.dialog_habit_delete) {
    private val manageViewModel : ManageViewModel by activityViewModels()


    override fun initAfterBinding() {
        initListener()
    }

    private fun initListener(){
        binding.deleteHabitDialogCancelBtn.setOnClickListener {
            dismiss()
        }

        binding.deleteHabitDialogDeleteHabitBtn.setOnClickListener {
            manageViewModel.deleteTodo(position , habitPosition)
            //삭제 했다는 리턴 값 보내야함 -> 그 뒤 어댑터 초기화
            val bundle = bundleOf("delete" to true) //삭제되었다고 보내기
            requireActivity().supportFragmentManager.setFragmentResult("request",bundle)
            dismiss()
        }
    }
}
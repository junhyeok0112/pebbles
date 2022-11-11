package com.todaypebble.pebbles.view.manage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.todaypebble.pebbles.data.remote.dto.manage.MyStone
import com.todaypebble.pebbles.domain.usecase.manage.GetMyStonesUseCase
import com.todaypebble.pebbles.util.getUserIdx
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageViewModel @Inject constructor(
    private val getMyStonesUseCase: GetMyStonesUseCase
) : ViewModel(){

    var stoneList = MutableLiveData<List<MyStone>?>()

    init {
        Log.d("ManageViewModel" , "실행2")
        viewModelScope.async{
            Log.d("ManageViewModel" , "실행3")
            stoneList.value = getMyStonesUseCase(getUserIdx())
            Log.d("ManageViewModel" , "실행11")
        }
    }


}
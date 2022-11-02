package com.example.pebbles.domain.usecase

import com.example.pebbles.data.remote.model.Habit
import com.example.pebbles.domain.repository.HomeRepository

//HabitList를 가져오는 UseCase로 Repository를 이용해야한다 . 따라서 DI로 Repository 넣어줘야함
//이것을 ViewModel에서 이용
interface GetHabitsFromAPIUseCase {

    suspend operator fun invoke() : ArrayList<Habit>?

}
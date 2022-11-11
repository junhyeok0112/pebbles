package com.todaypebble.pebbles.di.core

import com.todaypebble.pebbles.domain.usecase.*
import com.todaypebble.pebbles.domain.usecase.login.*
import com.todaypebble.pebbles.domain.usecase.logout.WithdrawalUseCase
import com.todaypebble.pebbles.domain.usecase.logout.WithdrawalUseCaseImpl
import com.todaypebble.pebbles.domain.usecase.manage.GetMyStonesUseCase
import com.todaypebble.pebbles.domain.usecase.manage.GetMyStonesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//UseCase들을 매개변수로 넘기기 위해서 생성 - >즉 매개변수로 넘기는 것들을 전부 DI 생성한다 생각하자.

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    //내부 DB에서 사용하는 useCase
    @Singleton
    @Binds
    abstract fun provideGetHabitFromDBUseCase(getHabitsFromDBUseCaseImpl: GetHabitsFromDBUseCaseImpl) : GetHabitsFromDBUseCase

    @Singleton
    @Binds
    abstract fun provideGetTodayFromDBUseCase(getTodayFromDBUseCaseImpl: GetTodayFromDBUseCaseImpl) : GetTodayFromDBUseCase

    @Singleton
    @Binds
    abstract fun provideUpdateTodoToDBUseCase(updateTodoToDBUseCaseImpl: UpdateTodoToDBUseCaseImpl) : UpdateTodoToDBUseCase

    //외부 Retrofit에서 사용하는 UseCase
    @Singleton
    @Binds
    abstract fun provideGetHabitsFromAPIUseCase(getHabitsFromAPIUseCaseImpl: GetHabitsFromAPIUseCaseImpl) : GetHabitsFromAPIUseCase

    @Singleton
    @Binds
    abstract fun provideUpdateHabitsToAPIUseCase(updateHabitsToAPIUseCaseImpl: UpdateHabitsToAPIUseCaseImpl) : UpdateHabitsToAPIUseCase

    //로그인 관련 UseCAse
    @Singleton
    @Binds
    abstract fun provideSignUpUseCase(signUpUseCaseImpl: SignUpUseCaseImpl) : SignUpUseCase

    @Singleton
    @Binds
    abstract fun provideLoginUseCase(loginUseCaseImpl: LoginUseCaseImpl) : LoginUseCase

    @Singleton
    @Binds
    abstract fun provideDuplicateChkUseCase(duplicateChkUseCaseImpl: DuplicateChkUseCaseImpl) : DuplicateChkUseCase

    //로그아웃
    @Singleton
    @Binds
    abstract fun provideLogoutUseCase(withdrawalUseCaseImpl: WithdrawalUseCaseImpl) : WithdrawalUseCase

    //내 하이라이트 조회
    @Singleton
    @Binds
    abstract fun provideGetMyStonesUseCase(getMyStonesUseCaseImpl: GetMyStonesUseCaseImpl) : GetMyStonesUseCase

}
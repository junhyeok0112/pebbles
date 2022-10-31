package com.example.pebbles.di.core

import com.example.pebbles.data.model.HabitDAO
import com.example.pebbles.data.repository.home.datasource.HabitLocalDataSource
import com.example.pebbles.data.repository.home.datasourceImpl.HabitLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//이 모듈에서 LocalDataSourceImpl을 생성하고 리턴함 -> 파라미터로 dao가짐 따라서 여기서도 dao를 파라미터로

@Module
class LocalDataModule{

    //HabitLocalDataSource 는 인터페이스이고 implmented된다 Impl에 의해서 따라서 Impl을 return 해준다

    @Singleton
    @Provides
    fun provideHabitLocalDataSource(habitDAO: HabitDAO) : HabitLocalDataSource{
        return HabitLocalDataSourceImpl(habitDAO)
    }

}
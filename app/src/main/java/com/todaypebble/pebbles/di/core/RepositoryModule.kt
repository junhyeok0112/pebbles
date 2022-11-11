package com.todaypebble.pebbles.di.core

import com.todaypebble.pebbles.data.repository.home.HomeRepositoryImpl
import com.todaypebble.pebbles.data.repository.login.LoginRepositoryImpl
import com.todaypebble.pebbles.data.repository.manage.ManageRepositoryImpl
import com.todaypebble.pebbles.domain.repository.HomeRepository
import com.todaypebble.pebbles.domain.repository.LogInRepository
import com.todaypebble.pebbles.domain.repository.ManageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//UseCase들이 Repository를 매개변수로 받기 때문에 이러한 Repository 의존정을 제공해야합니다.
//따라서 RepositoryModule을 만듭니다.

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    //이미 DataSource 모듈을 만들어놨기 때문에 이렇게 직접적으로 사용가능

    @Singleton
    @Binds
    abstract fun provideHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ) : HomeRepository


    @Singleton
    @Binds
    abstract fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl) : LogInRepository

    //바윗돌 관리 Repository
    @Singleton
    @Binds
    abstract fun provideManageRepository(manageRepositoryImpl: ManageRepositoryImpl) : ManageRepository
}
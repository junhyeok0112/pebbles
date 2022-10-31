package com.example.pebbles.di.core

import com.example.pebbles.di.home.HomeSubComponent
import dagger.Component
import javax.inject.Singleton

//Component Interface 생성 , Component Annotaion 사용
//List 제공해야함
//ApplicationComponent 어노테이션으로 사용하고 전체 Application에서 재사용하고 싶을 때 마다 사용 가능
//전체에서 SingleTon으로 사용하기위해 계속 Provider 붙힘?
@Singleton
@Component(modules = [
    AppModule::class,
NetModule::class,
DataBaseModule::class,
LocalDataModule::class,
RemoteDataModule::class,
RepositoryModule::class,
UseCaseModule::class
])
interface AppComponent {
    //여기에 Factory 메소드 작성하고 subComponentes 만들것
    fun homeSubComponent() : HomeSubComponent.Factory
}
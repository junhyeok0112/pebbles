package com.example.pebbles.di.home

import com.example.pebbles.view.home.fragment.HomeFragment
import dagger.Subcomponent
//SubComponent 인터페이스\

//이 서브컴포넌트랑 관련된 모듈들 작성 지금은 1개만 가짐
//이 서브컴포넌트를 HomeFragment에 의존성 주입하는데 쓸거임
//그러므로 주입 함수를 선언해야함 HomeFragment를 파라미터로하는
@HomeScope
@Subcomponent(modules = [HomeModule::class])
interface HomeSubComponent {
    fun inject(homeFragment: HomeFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create() : HomeSubComponent
    }
}
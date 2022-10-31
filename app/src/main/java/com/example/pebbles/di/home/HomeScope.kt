package com.example.pebbles.di.home

import javax.inject.Scope

//HomeData를 위한 Scope 생성 , Dagger Scope 만드는 것은 매우 직관적
//HomeViewModel Factory 의존성으로서 사용할 수 있는 스코프
@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class HomeScope

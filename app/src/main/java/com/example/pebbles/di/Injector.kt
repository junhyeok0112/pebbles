package com.example.pebbles.di

import com.example.pebbles.di.home.HomeSubComponent

interface Injector {


    fun createHomeSubComponent() : HomeSubComponent

}
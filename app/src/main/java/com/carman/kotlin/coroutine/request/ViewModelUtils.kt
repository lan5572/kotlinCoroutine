package com.carman.kotlin.coroutine.request

import com.carman.kotlin.coroutine.request.repository.MainRepository

object ViewModelUtils {
    fun provideMainViewModelFactory(
    ): MainViewModelFactory {
        return MainViewModelFactory(MainRepository())
    }
}
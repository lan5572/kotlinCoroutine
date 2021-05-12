package com.carman.kotlin.coroutine.request

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.carman.kotlin.coroutine.request.repository.MainRepository
import com.carman.kotlin.coroutine.request.viewmodel.MainViewModel
import java.lang.NullPointerException

object ViewModelUtils {

    fun <T> createViewModelFactory(activity: ComponentActivity, viewModel: Class<T>): T {
        return when {
            viewModel as? Class<MainViewModel> != null -> {
                ViewModelProvider(
                    activity,
                    provideMainViewModelFactory()
                ).get(viewModel) as T
            }
            else -> {
                throw NullPointerException()
            }
        }
    }

    fun provideMainViewModelFactory(
    ): MainViewModelFactory {
        return MainViewModelFactory(MainRepository())
    }
}
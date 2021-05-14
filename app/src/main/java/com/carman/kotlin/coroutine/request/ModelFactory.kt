package com.carman.kotlin.coroutine.request

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carman.kotlin.coroutine.request.repository.MainRepository
import com.carman.kotlin.coroutine.request.viewmodel.MainViewModel
import javax.inject.Inject


fun provideMainViewModelFactory(
): MainViewModelFactory {
    return MainViewModelFactory(MainRepository())
}

class MainViewModelFactory @Inject constructor(
    private val repository: MainRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}

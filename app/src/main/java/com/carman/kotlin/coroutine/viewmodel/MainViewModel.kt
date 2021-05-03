package com.carman.kotlin.coroutine.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.carman.kotlin.coroutine.extensions.delayMain
import com.carman.kotlin.coroutine.extensions.requestIO
import com.carman.kotlin.coroutine.extensions.requestMain

class MainViewModel:ViewModel() {

    init {
        requestMain {
            Log.d("MainViewModel", "主线程中启动协程")
        }
        requestIO {
            Log.d("MainViewModel", "IO线程中启动协程进行网络加载")
        }
        delayMain(100){
            Log.d("MainViewModel", "主线程中启动协程并延时一定时间")
        }
    }

}
package com.carman.kotlin.coroutine

import android.content.Intent
import android.os.IBinder
import com.carman.kotlin.coroutine.base.BaseService

class MainService : BaseService() {

    override fun onBind(intent: Intent): IBinder?  = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        requestIO {
            //网络加载
        }
        return super.onStartCommand(intent, flags, startId)
    }
}
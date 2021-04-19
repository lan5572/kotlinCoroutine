package com.carman.kotlin.coroutine.exception

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报
 *
 */
class GlobalCoroutineExceptionHandler(private val errCode: Int, private val errMsg: String? = "", private val report: Boolean = false) : CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*>
        get() = CoroutineExceptionHandler

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        Log.e("$errCode","----------------异常警告----------------")
        Log.e("$errCode","---------------------------------------")
        Log.e("$errCode","---------------------------------------")
        val msg =  exception.stackTraceToString()
        Log.e("$errCode","GlobalCoroutineExceptionHandler:${msg}")
        if (report){
           //上报日志
        }
        Log.e("$errCode","---------------------------------------")
        Log.e("$errCode","---------------------------------------")
        Log.e("$errCode","----------------异常警告----------------")
    }

}
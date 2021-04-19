package com.carman.kotlin.coroutine.extensions

import android.app.Activity
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.carman.kotlin.coroutine.exception.GlobalCoroutineExceptionHandler
import kotlinx.coroutines.*

fun Activity.getLoginIc(): String {
    val cm = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    cm.primaryClip?.let {
        val item = if (it.itemCount > 0) it.getItemAt(0) else null
        val ic = item?.text?.toString() ?: ""
        Log.d("clipData","clipData ic :$ic")
        return ic
    }
    return ""
}

/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报bugly
 * @param block 需要执行的任务
 */
inline fun AppCompatActivity.requestMain(errCode:Int = -1, errMsg:String = "",
                                         report:Boolean = false, crossinline block:suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(Dispatchers.Main + GlobalCoroutineExceptionHandler(errCode,errMsg, report)){
            block()
    }
}


/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报bugly
 * @param block 需要执行的任务
 */
inline fun AppCompatActivity.requestIO(errCode:Int = -1, errMsg:String = "", report:Boolean = false, crossinline block: suspend CoroutineScope.() -> Unit):Job{
    return lifecycleScope.launch(Dispatchers.IO + GlobalCoroutineExceptionHandler(errCode,errMsg, report)) {
        block()
    }
}

/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报bugly
 * @param block 需要执行的任务
 */
inline fun AppCompatActivity.delayMainThread(errCode:Int = -1, errMsg:String = "", report:Boolean = false, delayTime: Long, crossinline block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(Dispatchers.Main+GlobalCoroutineExceptionHandler(errCode,errMsg, report)) {
        withContext(Dispatchers.IO) {
            delay(delayTime)
        }
        block()
    }
}

/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报bugly
 * @param block 需要执行的任务
 */
inline fun Fragment.requestMain(errCode:Int = -1, errMsg:String = "", report:Boolean = false, crossinline block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(Dispatchers.Main+GlobalCoroutineExceptionHandler(errCode,errMsg, report)) {
        block()
    }
}
/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报bugly
 * @param block 需要执行的任务
 */
inline fun Fragment.requestIO(errCode:Int = -1, errMsg:String = "", report:Boolean = false, crossinline block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(Dispatchers.IO+GlobalCoroutineExceptionHandler(errCode,errMsg, report)) {
        block()
    }
}


/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报bugly
 * @param delayTime 延时时间
 * @param block 需要执行的任务
 */
inline fun Fragment.delayMainThread(errCode:Int = -1, errMsg:String = "", report:Boolean = false, delayTime: Long, crossinline block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(Dispatchers.Main + GlobalCoroutineExceptionHandler(errCode,errMsg, report)) {
        withContext(Dispatchers.IO) {
            delay(delayTime)
        }
        block()
    }
}



/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报bugly
 * @param block 需要执行的任务
 */
inline fun LifecycleCoroutineScope.requestMain(errCode:Int = -1, errMsg:String = "", report:Boolean = false, crossinline block: suspend CoroutineScope.() -> Unit) {
    launch(Dispatchers.Main+GlobalCoroutineExceptionHandler(errCode,errMsg, report)) {
        block()
    }
}

/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报bugly
 * @param block 需要执行的任务
 */
inline fun LifecycleCoroutineScope.requestIO(errCode:Int = -1, errMsg:String = "", report:Boolean = false, crossinline block: suspend CoroutineScope.() -> Unit) {
    launch(Dispatchers.IO + GlobalCoroutineExceptionHandler(errCode,errMsg, report)) {
        block()
    }
}


/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报bugly
 * @param block 需要执行的任务
 */
inline fun View.requestMain(errCode:Int = -1, errMsg:String = "", report:Boolean = false, crossinline block: suspend CoroutineScope.() -> Unit) {
    if (context is ComponentActivity){
        (context as ComponentActivity).lifecycleScope.launch(Dispatchers.Main+GlobalCoroutineExceptionHandler(errCode,errMsg, report)) {
            block()
        }
    }else if (context is Fragment){
        (context as ComponentActivity).lifecycleScope.launch(Dispatchers.Main+GlobalCoroutineExceptionHandler(errCode,errMsg, report)) {
            block()
        }
    }
}





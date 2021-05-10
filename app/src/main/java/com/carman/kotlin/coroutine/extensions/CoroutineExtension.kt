package com.carman.kotlin.coroutine.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.carman.kotlin.coroutine.exception.GlobalCoroutineExceptionHandler
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@Suppress("FunctionName")
public fun NormalScope(): CoroutineScope = CoroutineScope(Dispatchers.Main)

/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报
 * @param block 需要执行的任务
 */
inline fun AppCompatActivity.requestMain(
        errCode: Int = -1, errMsg: String = "", report: Boolean = false,
        noinline block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(GlobalCoroutineExceptionHandler(errCode, errMsg, report)) {
        block.invoke(this)
    }
}


/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报
 * @param block 需要执行的任务
 */
inline fun AppCompatActivity.requestIO(
        errCode: Int = -1, errMsg: String = "", report: Boolean = false,
        noinline block: suspend CoroutineScope.() -> Unit): Job {
    return lifecycleScope.launch(Dispatchers.IO + GlobalCoroutineExceptionHandler(errCode, errMsg, report)) {
        block.invoke(this)
    }
}

/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报
 * @param block 需要执行的任务
 */
inline fun AppCompatActivity.delayMain(
        delayTime: Long,errCode: Int = -1, errMsg: String = "", report: Boolean = false,
        noinline block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(GlobalCoroutineExceptionHandler(errCode, errMsg, report)) {
        withContext(Dispatchers.IO) {
            delay(delayTime)
        }
        block.invoke(this)
    }
}

/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报
 * @param block 需要执行的任务
 */
inline fun Fragment.requestMain(
        errCode: Int = -1, errMsg: String = "", report: Boolean = false,
        noinline block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(GlobalCoroutineExceptionHandler(errCode, errMsg, report)) {
        block.invoke(this)
    }
}

/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报
 * @param block 需要执行的任务
 */
inline fun Fragment.requestIO(
        errCode: Int = -1, errMsg: String = "", report: Boolean = false,
        noinline block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(Dispatchers.IO + GlobalCoroutineExceptionHandler(errCode, errMsg, report)) {
        block.invoke(this)
    }
}


/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报
 * @param delayTime 延时时间
 * @param block 需要执行的任务
 */
inline fun Fragment.delayMain(
        delayTime: Long, errCode: Int = -1, errMsg: String = "", report: Boolean = false,
        noinline block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(GlobalCoroutineExceptionHandler(errCode, errMsg, report)) {
        withContext(Dispatchers.IO) {
            delay(delayTime)
        }
        block.invoke(this)
    }
}




/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报
 * @param block 需要执行的任务
 */
inline fun ViewModel.requestMain(
        errCode: Int = -1, errMsg: String = "", report: Boolean = false,
        noinline block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(GlobalCoroutineExceptionHandler(errCode, errMsg, report)) {
        block.invoke(this)
    }
}

/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报
 * @param block 需要执行的任务
 */
inline fun ViewModel.requestIO(
        errCode: Int = -1, errMsg: String = "", report: Boolean = false,
        noinline block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(Dispatchers.IO + GlobalCoroutineExceptionHandler(errCode, errMsg, report)) {
        block.invoke(this)
    }
}


/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报
 * @param delayTime 延时时间
 * @param block 需要执行的任务
 */
inline fun ViewModel.delayMain(
        delayTime: Long, errCode: Int = -1, errMsg: String = "", report: Boolean = false,
        noinline block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(GlobalCoroutineExceptionHandler(errCode, errMsg, report)) {
        withContext(Dispatchers.IO) {
            delay(delayTime)
        }
        block.invoke(this)
    }
}

inline fun LifecycleCoroutineScope.requestMain(
    errCode: Int = -1, errMsg: String = "", report: Boolean = false,
    noinline block: suspend CoroutineScope.() -> Unit) {
    launch(GlobalCoroutineExceptionHandler(errCode, errMsg, report)) {
        block.invoke(this)
    }
}

@ExperimentalStdlibApi
suspend inline fun <T> CoroutineScope.delayWithContext(delayTime: Long, context: CoroutineContext, noinline block: suspend CoroutineScope.() -> T) {
    if (context[CoroutineDispatcher] is MainCoroutineDispatcher) {
        withContext(Dispatchers.IO) {
            delay(delayTime)
        }
    } else {
        delay(delayTime)
    }
    block.invoke(this)

}

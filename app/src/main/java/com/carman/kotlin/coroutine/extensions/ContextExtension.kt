package com.carman.kotlin.coroutine.extensions

import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Process


fun Context.isAPPInTop(): Boolean {
    val packageName: String = getProcessName() ?:""
    val activityManager: ActivityManager =
        getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val listInfos: List<ActivityManager.RunningTaskInfo> = activityManager.getRunningTasks(20)
    // 判断程序是否在栈顶
    return if (!listInfos.isNullOrEmpty()){
        listInfos[0].topActivity?.packageName == packageName
    }else false
}

/**
 * 返回app运行状态
 * @param context 一个context
 * @return int 1:前台 2:后台 0:不存在
 */
fun Context.isAppAlive(): Int {
    val packageName: String = getProcessName() ?:""
    val activityManager: ActivityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val listInfos: List<ActivityManager.RunningTaskInfo> = activityManager.getRunningTasks(20)

    return if (listInfos[0].topActivity?.packageName == packageName) {
        1
    } else {
        for (info in listInfos) {
            if (info.topActivity?.packageName == packageName) {
                return 2
            }
        }
        0
    }
}

fun Context.getProcessName(): String? {
    var manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    for (processInfo in manager.runningAppProcesses) {
        if (processInfo.pid == Process.myPid()) {
            return processInfo.processName
        }
    }
    return null
}

fun Context.checkAppInstalled(pckName: String): Boolean {
    var packageInfo: PackageInfo? = null
    try {
        packageInfo = packageManager.getPackageInfo(pckName, 0)
    } catch (e: PackageManager.NameNotFoundException) {
    }
    return packageInfo != null
}
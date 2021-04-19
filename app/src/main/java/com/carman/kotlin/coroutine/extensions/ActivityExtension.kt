package com.carman.kotlin.coroutine.extensions

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) {
    startActivity(Intent(this, T::class.java).putExtras(bundleOf(*params)))
}
inline fun <reified T : Activity> Context.getIntent(vararg params: Pair<String, Any?>):Intent {
    return Intent(this, T::class.java).putExtras(bundleOf(*params))
}

inline fun <reified T : Activity> FragmentActivity.startActivity(vararg params: Pair<String, Any?>) {
    startActivity(Intent(this, T::class.java).putExtras(bundleOf(*params)))
}

inline fun <reified T : Activity> FragmentActivity.startActivityForResult(requestCode:Int,vararg params: Pair<String, Any?>) {
    startActivityForResult(Intent(this, T::class.java).putExtras(bundleOf(*params)),requestCode)
}

inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any?>) {
    startActivity(Intent(this.context, T::class.java).putExtras(bundleOf(*params)))
}

inline fun <reified T : Activity> Fragment.startActivity(requestCode:Int,vararg params: Pair<String, Any?>) {
    startActivityForResult(Intent(this.context, T::class.java).putExtras(bundleOf(*params)),requestCode)
}


inline fun Context.getRunningActivityName(): String {
    val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        val listInfos: List<ActivityManager.RunningTaskInfo>  = activityManager.getRunningTasks(1)
         if (!listInfos.isNullOrEmpty()){
            listInfos[0].topActivity?.className?:""
        }else ""
    }else{
        val listInfos = activityManager.appTasks
        if (!listInfos.isNullOrEmpty()){
            listInfos[0].taskInfo.topActivity?.className?:""
        }else ""
    }
}

  /**
 * 重置状态栏
 */
  inline fun Activity.resetTopStatusBarColor(isLightStatusBar: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
       window.decorView.let {
            it.systemUiVisibility =
                if (isLightStatusBar) it.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv() else it.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    } else {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        if (isLightStatusBar) {
           window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        } else {
           window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
}

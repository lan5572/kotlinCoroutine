package com.carman.kotlin.coroutine.interf

import android.view.View
//import io.reactivex.Observable
//import io.reactivex.ObservableEmitter
//import io.reactivex.ObservableOnSubscribe
//import java.util.concurrent.TimeUnit

object DoubleClickHelper {
    private const val windowDuration = 1
    @JvmOverloads
    fun click(view: View?, r: Runnable?, durationSeconds: Int = windowDuration) {
        if (view == null || r == null) {
            return
        }
//        Observable.create<View>(ClickObservable(view))
//            .throttleFirst(durationSeconds.toLong(), TimeUnit.SECONDS)
//            .subscribe { o: View? -> r.run() }
    }

    @JvmOverloads
    fun click(view: View?, listener: View.OnClickListener?, durationSeconds: Int = windowDuration) {
        if (view == null || listener == null) {
            return
        }
//        Observable.create<View>(ClickObservable(view))
//            .throttleFirst(durationSeconds.toLong(), TimeUnit.SECONDS)
//            .subscribe { o: View? -> listener.onClick(view) }
    }
}

//internal class ClickObservable(view: View) : ObservableOnSubscribe<View> {
//    private var mEmitter: ObservableEmitter<View>? = null
//    @Throws(Exception::class)
//    override fun subscribe(e: ObservableEmitter<View>) {
//        mEmitter = e
//    }
//
//    init {
//        view.setOnClickListener { view ->
//            if (mEmitter != null) {
//                mEmitter!!.onNext(view)
//            }
//        }
//    }
//}
package com.carman.kotlin.coroutine.remote

import com.carman.kotlin.coroutine.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpClientManager {

     val mClient: OkHttpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        buildClient()
    }

    private fun buildClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder().apply {
            addInterceptor(CommonInterceptor())
            addInterceptor(logging)
//            addInterceptor(ErrorInterceptor())
            followSslRedirects(true)
        }.build()
    }
}
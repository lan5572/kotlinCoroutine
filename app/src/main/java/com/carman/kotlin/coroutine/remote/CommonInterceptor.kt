package com.carman.kotlin.coroutine.remote

import android.os.Build
import com.carman.kotlin.coroutine.constant.HttpConstant
import com.carman.kotlin.coroutine.constant.HttpConstant.SHOW_API_APPID
import com.carman.kotlin.coroutine.constant.HttpConstant.SHOW_API_SIGN
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class CommonInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val httpUrl = oldRequest.url
        val host = httpUrl.host
        if (HttpConstant.SERVER_HOST != host ) {
            return chain.proceed(oldRequest)
        }
        val urlBuilder = httpUrl.newBuilder()
        urlBuilder.addQueryParameter("showapi_appid", SHOW_API_APPID)
        urlBuilder.addQueryParameter("showapi_sign", SHOW_API_SIGN)

        val request = oldRequest
            .newBuilder()
            .url(urlBuilder.build())
            .build()
        return chain.proceed(request)
    }
}
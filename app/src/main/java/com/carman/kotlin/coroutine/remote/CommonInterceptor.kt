package com.carman.kotlin.coroutine.remote

import android.os.Build
import com.carman.kotlin.coroutine.constant.HttpConstant
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class CommonInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val httpUrl = oldRequest.url
        val host = httpUrl.host
        if (HttpConstant.HTTP_SERVER != host ) {
            return chain.proceed(oldRequest)
        }
        val urlBuilder = httpUrl.newBuilder()
        urlBuilder.addQueryParameter("showapi_appid", "628308")
        urlBuilder.addQueryParameter("showapi_sign", "a7a8d4b8f23b432e9d66cabc9619a216")

        val request = oldRequest
            .newBuilder()
            .url(urlBuilder.build())
            .build()
        return chain.proceed(request)
    }

}
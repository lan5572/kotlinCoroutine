package com.carman.kotlin.coroutine.remote

import com.carman.kotlin.coroutine.constant.HttpConstant
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import okio.GzipSource
import org.json.JSONObject
import java.io.IOException

class ErrorInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val oldRequest = chain.request()
        val host = oldRequest.url.host
        if (HttpConstant.HTTP_SERVER != host) {
            return response
        }

        if (response.isSuccessful) {
            val headers = response.headers
            val responseStr = response.body?.let {
                val source = it.source()
                source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
                var buffer = source.buffer

                if ("gzip".equals(headers.get("Content-Encoding"), ignoreCase = true)) {
                    GzipSource(buffer.clone()).use { gzippedResponseBody ->
                        buffer = Buffer()
                        buffer.writeAll(gzippedResponseBody)
                    }
                }
                buffer.clone().readString(Charsets.UTF_8)
            }
            if (!responseStr.isNullOrEmpty()) {
                val responseJson = try {
                    JSONObject(responseStr)
                } catch (e: Exception) {
                    return response
                }
                when (responseJson.getInt("code")) {
                    HttpConstant.TOKEN_EXPIRED -> {
                        // token过期，刷新token
                        return response
                    }
                    HttpConstant.INVALID_TOKEN -> {
                        // token 错误，登出账户
                        return  response
                    }
                }
            }
        }
        return response
    }

}
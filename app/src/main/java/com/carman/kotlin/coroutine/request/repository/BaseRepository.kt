package com.carman.kotlin.coroutine.request.repository

import com.carman.kotlin.coroutine.constant.HttpConstant
import com.carman.kotlin.coroutine.remote.CResponse

open class BaseRepository {
    suspend fun <T : Any> handResponse(response: CResponse<T>, onSuccess: suspend () -> Unit, errorBlock:suspend () -> Unit){
        when{
            response == null -> errorBlock()
            response.code == HttpConstant.OK -> onSuccess()
            else -> errorBlock()
        }

    }
}
package com.carman.kotlin.coroutine.remote

import com.google.gson.annotations.SerializedName

data class CResponse<T>(
    @SerializedName("showapi_res_code")
    val code: Int,
    @SerializedName("showapi_res_error")
    val msg: String? = null,
    @SerializedName("showapi_res_body")
    val data: T
)

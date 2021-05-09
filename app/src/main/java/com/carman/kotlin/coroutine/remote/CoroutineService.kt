package com.carman.kotlin.coroutine.remote

import com.carman.kotlin.coroutine.bean.SysConfig
import com.carman.kotlin.coroutine.bean.Weather
import retrofit2.http.*

interface CoroutineService {

    /**
     * app启动
     */
    @GET("public/app/start")
    suspend fun getWeather(): CResponse<Weather>

    /**
     * 获取系统全局配置
     */
    @GET("sys/config")
    suspend fun getSysConfig(): CResponse<SysConfig>

    @FormUrlEncoded
    @POST("/user/push/reg")
    suspend fun registerPushId(
        @Field("plat") plat: String,
        @Field("identifier") identifier: String
    ): CResponse<Any>

}
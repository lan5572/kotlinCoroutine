package com.carman.kotlin.coroutine.request.repository

import com.carman.kotlin.coroutine.bean.Weather
import com.carman.kotlin.coroutine.remote.CResponse
import com.carman.kotlin.coroutine.remote.ServerApi

class MainRepository :BaseRepository(){

    suspend fun getWeather(
        area: String
    ): CResponse<Weather>{
        return ServerApi.service.getWeather(area)
    }

}
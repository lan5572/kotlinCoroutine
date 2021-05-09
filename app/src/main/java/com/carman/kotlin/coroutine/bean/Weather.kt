package com.carman.kotlin.coroutine.bean

import com.google.gson.annotations.SerializedName


data class Weather(
    val now: WeatherDetail,
    val time: String
)

data class WeatherDetail(
    val aqi: String,
    val rain: String,
    val sd: String,
    val temperature: String,
    @SerializedName("temperature_time")
    val temperatureTime: String,
    val weather: String,
    @SerializedName("weather_pic")
    val weatherPic: String,
    @SerializedName("wind_direction")
    val windDirection: String,
    @SerializedName("windPower")
    val windPower: String
)

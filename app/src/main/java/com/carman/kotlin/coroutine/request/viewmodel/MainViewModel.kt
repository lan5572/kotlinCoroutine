package com.carman.kotlin.coroutine.request.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carman.kotlin.coroutine.R
import com.carman.kotlin.coroutine.bean.User
import com.carman.kotlin.coroutine.bean.Weather
import com.carman.kotlin.coroutine.extensions.requestMain
import com.carman.kotlin.coroutine.request.repository.MainRepository

class MainViewModel(private val repository: MainRepository):ViewModel() {
    private val _user:MutableLiveData<User> = MutableLiveData()
    val mUser: LiveData<User> = _user


    private val _weather:MutableLiveData<Weather> = MutableLiveData()
    val mWeather: LiveData<Weather> = _weather

    fun getWeather( area: String){
        requestMain {
           val result = try {
               repository.getWeather(area)
           } catch (e: Exception) {
               e.printStackTrace()
               null
           }
            _weather.postValue(result?.data)
        }
    }

    fun onClick(v:View){
        when (v.id){
            R.id.request_tbn ->{
                getWeather("深圳")
            }
        }
    }

}
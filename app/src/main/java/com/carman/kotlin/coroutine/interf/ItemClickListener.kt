package com.carman.kotlin.coroutine.interf

import android.view.View

interface ItemClickListener<T> {
    fun onItemClick(view: View,position:Int, data: T){}
    fun onItemClick(view: View, data: T){}
}
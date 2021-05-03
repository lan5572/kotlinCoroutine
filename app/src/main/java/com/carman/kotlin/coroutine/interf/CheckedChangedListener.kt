package com.carman.kotlin.coroutine.interf

import android.widget.CompoundButton

interface CheckedChangedListener<T> {
    fun onCheckedChanged(buttonView: CompoundButton, isChecked:Boolean,data: T)
}
package com.carman.kotlin.coroutine.base

import androidx.databinding.ViewDataBinding

interface BaseBinding<VB : ViewDataBinding> {
    fun VB.initBinding()
}
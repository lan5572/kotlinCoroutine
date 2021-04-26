package com.carman.kotlin.coroutine.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType


inline fun <VB:ViewBinding> Any.getViewBinding(inflater: LayoutInflater):VB{
    val vbClass =  (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<VB>>()
    val inflate = vbClass[0].getDeclaredMethod("inflate", LayoutInflater::class.java)
    return  inflate.invoke(null, inflater) as VB
}


inline fun <VB:ViewBinding> Any.getViewBinding(inflater: LayoutInflater, container: ViewGroup?):VB{
    val vbClass =  (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<VB>>()
    val inflate = vbClass[0].getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    return inflate.invoke(null, inflater, container, false) as VB
}
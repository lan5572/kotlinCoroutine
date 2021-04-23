package com.carman.kotlin.coroutine.extensions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType


inline fun <VB:ViewBinding> Any.getViewBinding(inflater: LayoutInflater, position:Int = 0):VB{
    val vbClass: Class<VB> = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[position] as Class<VB>
    val inflate = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
    return  inflate.invoke(null, inflater) as VB
}


inline fun <VB:ViewBinding> Any.getViewBinding(inflater: LayoutInflater, container: ViewGroup?, position:Int = 0):VB{
    val vbClass: Class<VB> = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[position] as Class<VB>
    val inflate = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    return inflate.invoke(null, inflater, container, false) as VB
}
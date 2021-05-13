package com.carman.kotlin.coroutine.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.carman.kotlin.coroutine.base.BaseMultiTypeAdapter
import com.carman.kotlin.coroutine.request.ViewModelUtils
import com.carman.kotlin.coroutine.request.viewmodel.MainViewModel
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


inline fun <VB:ViewBinding> Any.getViewBinding(inflater: LayoutInflater,position:Int = 0):VB{
    val vbClass =  (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<*>>()
    val inflate = vbClass[position].getDeclaredMethod("inflate", LayoutInflater::class.java)
    return  inflate.invoke(null, inflater) as VB
}


inline fun <VB:ViewBinding> Any.getViewBinding(inflater: LayoutInflater, container: ViewGroup?,position:Int = 0):VB{
    val vbClass =  (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<VB>>()
    val inflate = vbClass[position].getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    return inflate.invoke(null, inflater, container, false) as VB
}

inline fun <VM: ViewModel> ComponentActivity.createViewModel(position:Int): VM {
    val vbClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<*>>()
    val viewModel = vbClass[position] as Class<VM>
    return ViewModelProvider(this).get(viewModel)
}

package com.carman.kotlin.coroutine.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import java.lang.reflect.ParameterizedType

/**
 *
 *@author carman
 * @time 2021-4-16 13:25
 */
open abstract class BaseFragment<VB : ViewDataBinding>: Fragment(),BaseBinding<VB> {
    internal lateinit var mBinding:VB
        private set
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vbClass: Class<VB> = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val inflate = vbClass.getDeclaredMethod("inflate",LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        mBinding = inflate.invoke(null, layoutInflater, container, false) as VB
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.initBinding()
    }
}
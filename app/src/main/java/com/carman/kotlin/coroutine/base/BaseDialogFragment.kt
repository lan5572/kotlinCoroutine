package com.carman.kotlin.coroutine.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import java.lang.reflect.ParameterizedType
/**
 *
 *@author carman
 * @time 2021-4-16 13:25
 */
abstract class BaseDialogFragment<VB : ViewDataBinding>  : DialogFragment(){
    protected lateinit var mBinding:VB

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val vbClass: Class<VB> = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val inflate = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        mBinding = inflate.invoke(null, layoutInflater, container, false) as VB
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.initBinding()
    }

    override fun onResume() {
        dialog?.let {
            val params: ViewGroup.LayoutParams? = it.window?.attributes
            params?.run {
                width = WindowManager.LayoutParams.MATCH_PARENT
                height = WindowManager.LayoutParams.MATCH_PARENT
                it.window?.attributes = this as WindowManager.LayoutParams
            }
        }
        super.onResume()
    }

    abstract fun VB.initBinding()
}
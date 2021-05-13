package com.carman.kotlin.coroutine.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.carman.kotlin.coroutine.extensions.getViewBinding

/**
 *
 *@author carman
 * @time 2021-4-16 13:25
 */
abstract class BaseVBActivity<VB : ViewDataBinding> : AppCompatActivity(), BaseBinding<VB> {

    protected val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
       getViewBinding(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initObserve()
        mBinding.initBinding()
    }

    abstract fun initObserve()

}
package com.carman.kotlin.coroutine.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.carman.kotlin.coroutine.extensions.getViewBinding

/**
 *
 *@author carman
 * @time 2021-4-16 13:25
 */
abstract class BaseFragment<VB : ViewDataBinding>: Fragment(),BaseBinding<VB> {
    protected lateinit var mBinding:VB
        private set
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = getViewBinding(inflater,container)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.initBinding()
    }


    open fun onBackPressed():Boolean {
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        if(::mBinding.isInitialized){
            mBinding.unbind()
        }
    }
}
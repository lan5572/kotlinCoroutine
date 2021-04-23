package com.carman.kotlin.coroutine.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.carman.kotlin.coroutine.extensions.getViewBinding

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
}
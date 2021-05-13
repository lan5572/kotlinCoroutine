package com.carman.kotlin.coroutine.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carman.kotlin.coroutine.extensions.getViewBinding
import com.carman.kotlin.coroutine.request.ViewModelUtils

/**
 *
 *@author carman
 * @time 2021-4-16 13:25
 */
abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel>(
    private val factory: ViewModelProvider.Factory? = null
) : AppCompatActivity(), BaseBinding<VB> {
    protected val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        getViewBinding(layoutInflater)
    }
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        viewModel = ViewModelUtils.createViewModel(this, factory, 1)
        mBinding.initBinding()
        initObserve()
    }

    abstract fun initObserve()

    override fun onBackPressed() {
        super.onBackPressed()
        val fragments: List<Fragment> = supportFragmentManager.fragments
        if (!fragments.isNullOrEmpty()) {
            for (fragment in fragments) {
                if (fragment is BaseFragment<*, *>) {
                    if (fragment.onBackPressed()) {
                        return
                    }
                }
            }
        }
        super.onBackPressed()
    }
}
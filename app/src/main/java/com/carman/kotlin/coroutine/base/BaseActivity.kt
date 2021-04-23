package com.carman.kotlin.coroutine.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.carman.kotlin.coroutine.extensions.getViewBinding

/**
 *
 *@author carman
 * @time 2021-4-16 13:25
 */
abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity(), BaseBinding<VB> {

    internal val mBinding: VB by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
       getViewBinding(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
            finish()
            return
        }
        setContentView(mBinding.root)
        mBinding.initBinding()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fragments: List<Fragment> = supportFragmentManager.fragments
        if (!fragments.isNullOrEmpty()) {
            for (fragment in fragments) {
                if (fragment is BaseFragment<*>) {
                    if (fragment.onBackPressed()) {
                        return
                    }
                }
            }
        }
        super.onBackPressed()
    }
}
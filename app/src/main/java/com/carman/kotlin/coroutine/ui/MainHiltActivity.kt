package com.carman.kotlin.coroutine.ui

import android.util.Log
import androidx.activity.viewModels
import com.carman.kotlin.coroutine.base.BaseVBActivity
import com.carman.kotlin.coroutine.databinding.ActivityMainBinding
import com.carman.kotlin.coroutine.request.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * 项目实战主入口
 * @author carman
 * @time 2021-4-26 12:11
 */
@AndroidEntryPoint
class MainHiltActivity : BaseVBActivity<ActivityMainBinding>(){
    val viewModel:MainViewModel by viewModels()

    override fun initObserve() {
        viewModel.mUser.observe(this){
            Log.d("MainViewModel","user: $it") //MainViewModel: user: User(id=1, name=测试)
        }
    }

    override fun ActivityMainBinding.initBinding() {
        this.mainViewModel = viewModel
    }

}
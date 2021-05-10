package com.carman.kotlin.coroutine.ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carman.kotlin.coroutine.R
import com.carman.kotlin.coroutine.base.BaseActivity
import com.carman.kotlin.coroutine.bean.Person
import com.carman.kotlin.coroutine.bean.Student
import com.carman.kotlin.coroutine.bean.Teacher
import com.carman.kotlin.coroutine.databinding.ActivityMainBinding
import com.carman.kotlin.coroutine.extensions.*
import com.carman.kotlin.coroutine.interf.ItemClickListener
import com.carman.kotlin.coroutine.request.ViewModelUtils
import com.carman.kotlin.coroutine.request.repository.MainRepository
import com.carman.kotlin.coroutine.request.viewmodel.MainViewModel
import com.carman.kotlin.coroutine.ui.adapter.HomeAdapter
import com.carman.kotlin.coroutine.ui.adapter.SecondAdapter
import kotlinx.coroutines.*

/**
 * 项目实战主入口
 * @author carman
 * @time 2021-4-26 12:11
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelUtils.provideMainViewModelFactory()
    }

    override fun initObserve() {
        viewModel.mWeather.observe(this) {
            mBinding.contentTv.text = "$it"
        }
    }

    override fun ActivityMainBinding.initBinding() {
        this.mainViewModel = viewModel
    }

}
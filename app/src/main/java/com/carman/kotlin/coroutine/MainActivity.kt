package com.carman.kotlin.coroutine

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.carman.kotlin.coroutine.base.BaseActivity
import com.carman.kotlin.coroutine.databinding.ActivityMainBinding
import com.carman.kotlin.coroutine.extensions.delayWithContext
import kotlinx.coroutines.*

/**
 * 项目实战主入口
 * @author carman
 * @time 2021-4-26 12:11
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.inflate<ActivityMainBinding>(layoutInflater,R.layout.activity_main,null,false)
    }
    @ExperimentalStdlibApi
    override fun ActivityMainBinding.initBinding() {

        lifecycleScope.launch {
            delayWithContext(1010,Dispatchers.Main){
                Toast.makeText(this@MainActivity,"演示操作",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
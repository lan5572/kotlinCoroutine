package com.carman.kotlin.coroutine

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.carman.kotlin.coroutine.base.BaseActivity
import com.carman.kotlin.coroutine.databinding.ActivityMainBinding
import com.carman.kotlin.coroutine.extensions.*
import kotlinx.coroutines.*

/**
 * 项目实战主入口
 * @author carman
 * @time 2021-4-26 12:11
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestMain {
            delay(2000)
            Toast.makeText(this@MainActivity,"haha",Toast.LENGTH_SHORT).show()
        }
        requestIO {
            loadNetData()
        }
        delayMainThread(100){
            Toast.makeText(this@MainActivity,"haha",Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun loadNetData(){
        //网络加载
    }
}
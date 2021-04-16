package com.carman.kotlin.coroutine

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.btn)
        btn.setOnClickListener {
            start()
        }

    }

    private fun start(){
        val runBlockingJob = runBlocking {
            Log.d("Coroutine", "runBlocking启动一个协程")
        }
        val launchJob = GlobalScope.launch{
            Log.d("Coroutine", "launch启动一个协程")
        }

        val asyncJob = GlobalScope.async{
            Log.d("Coroutine", "async启动一个协程")
            1
        }
    }
}
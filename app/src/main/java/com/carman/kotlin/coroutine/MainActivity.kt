package com.carman.kotlin.coroutine

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    companion object Key : CoroutineContext.Key<ContinuationInterceptor>

    private lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.btn)
        btn.setOnClickListener {
//            start()
//            testCoroutineContext()
//            testCoroutineStart()
            testUnDispatched()
        }
    }

    private fun start() {

        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                //网络请求...
                "请求结果"
            }
            btn.text = result
        }

   /*     GlobalScope.launch(Dispatchers.Main) {
            for (index in 1 until  10) {
                //同步执行
                launch {
                    Log.d("launch$index", "启动一个协程")
                }
            }
        }*/

       /* GlobalScope.launch {
            for (index in 1 until  10) {
                //并发执行
                launch {
                    Log.d("launch$index", "启动一个协程")
                }
            }
        }*/


        /*GlobalScope.launch{
            val launchJob = launch{
                Log.d("launch", "启动一个协程")
            }
            Log.d("launchJob", "$launchJob")
            val asyncJob = async{
                Log.d("async", "启动一个协程")
                "我是async返回值"
            }
            Log.d("asyncJob.await", ":${asyncJob.await()}")
            Log.d("asyncJob", "$asyncJob")
        }*/

       /* val runBlockingJob = runBlocking {
            Log.d("runBlocking", "启动一个协程")
        }
        Log.d("runBlockingJob", "$runBlockingJob")
        val launchJob = GlobalScope.launch{
            Log.d("launch", "启动一个协程")
        }
        Log.d("launchJob", "$launchJob")
        val asyncJob = GlobalScope.async{
            Log.d("async", "启动一个协程")
            "我是返回值"
        }
        Log.d("asyncJob", "$asyncJob")*/


//        runBlocking {
//            Log.d("runBlocking", "启动一个协程")
//        }
//        GlobalScope.launch{
//            Log.d("launch", "启动一个协程")
//        }
//        GlobalScope.async{
//            Log.d("async", "启动一个协程")
//        }
    }

    private fun testCoroutineContext(){
        val coroutineContext1 = Job() + CoroutineName("这是第一个上下文")
        Log.d("coroutineContext1", "$coroutineContext1")
        val  coroutineContext2 = coroutineContext1 + Dispatchers.Default + CoroutineName("这是第二个上下文")
        Log.d("coroutineContext2", "$coroutineContext2")
        val coroutineContext3 = coroutineContext2 + Dispatchers.Main + CoroutineName("这是第三个上下文")
        Log.d("coroutineContext3", "$coroutineContext3")
    }

    private fun testCoroutineStart(){
        val defaultJob = GlobalScope.launch{
            Log.d("defaultJob", "CoroutineStart.DEFAULT")
        }
        defaultJob.cancel()

        val lazyJob = GlobalScope.launch(start = CoroutineStart.LAZY){
            Log.d("lazyJob", "CoroutineStart.LAZY")
        }

        val atomicJob = GlobalScope.launch(start = CoroutineStart.ATOMIC){
            Log.d("atomicJob", "CoroutineStart.ATOMIC挂起前")
            delay(100)
            Log.d("atomicJob", "CoroutineStart.ATOMIC挂起后")
        }

        atomicJob.cancel()

        val undispatchedJob = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED){
            Log.d("undispatchedJob", "CoroutineStart.UNDISPATCHED挂起前")
            delay(100)
            Log.d("atomicJob", "CoroutineStart.UNDISPATCHED挂起后")
        }
        undispatchedJob.cancel()
    }

    private fun testUnDispatched(){
       runBlocking (Dispatchers.Default){
           val job = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
               Log.d("${Thread.currentThread().name}线程", "-> 挂起前")
               delay(100)
               Log.d("${Thread.currentThread().name}线程", "-> 挂起后")
           }
           Log.d("${Thread.currentThread().name}线程", "-> join前")
           job.join()
           Log.d("${Thread.currentThread().name}线程", "-> join后")
       }
    }
}
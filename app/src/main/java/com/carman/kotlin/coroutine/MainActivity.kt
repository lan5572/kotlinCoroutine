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
//            testUnDispatched()
//            testCoroutineScope()
//            testCoroutineScope2()
//            testCoroutineScope4()
//            testCoroutineScope4()
            testException()
        }
    }

    private fun start() {

//        GlobalScope.launch(Dispatchers.Main) {
//            val result = withContext(Dispatchers.IO) {
//                //网络请求...
//                "请求结果"
//            }
//            btn.text = result
//        }

            /* GlobalScope.launch(Dispatchers.Main) {
                 for (index in 1 until  10) {
                     //同步执行
                     launch {
                         Log.d("launch$index", "启动一个协程")
                     }
                 }
             }*/

//         GlobalScope.launch {
//             for (index in 1 until  10) {
//                 //并发执行
//                 launch {
//                     Log.d("launch$index", "启动一个协程")
//                 }
//             }
//         }


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

    private fun testCoroutineContext() {
        val coroutineContext1 = Job() + CoroutineName("这是第一个上下文")
        Log.d("coroutineContext1", "$coroutineContext1")
        val coroutineContext2 = coroutineContext1 + Dispatchers.Default + CoroutineName("这是第二个上下文")
        Log.d("coroutineContext2", "$coroutineContext2")
        val coroutineContext3 = coroutineContext2 + Dispatchers.Main + CoroutineName("这是第三个上下文")
        Log.d("coroutineContext3", "$coroutineContext3")
    }

    private fun testCoroutineStart() {
        val defaultJob = GlobalScope.launch {
            Log.d("defaultJob", "CoroutineStart.DEFAULT")
        }
        defaultJob.cancel()

        val lazyJob = GlobalScope.launch(start = CoroutineStart.LAZY) {
            Log.d("lazyJob", "CoroutineStart.LAZY")
        }

        val atomicJob = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
            Log.d("atomicJob", "CoroutineStart.ATOMIC挂起前")
            delay(100)
            Log.d("atomicJob", "CoroutineStart.ATOMIC挂起后")
        }

        atomicJob.cancel()

        val undispatchedJob = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
            Log.d("undispatchedJob", "CoroutineStart.UNDISPATCHED挂起前")
            delay(100)
            Log.d("atomicJob", "CoroutineStart.UNDISPATCHED挂起后")
        }
        undispatchedJob.cancel()
    }

    private fun testUnDispatched() {
        GlobalScope.launch(Dispatchers.Main) {
            val job = launch(start = CoroutineStart.UNDISPATCHED) {
                Log.d("${Thread.currentThread().name}线程", "-> 挂起前")
                delay(100)
                Log.d("${Thread.currentThread().name}线程", "-> 挂起后")
            }
            Log.d("${Thread.currentThread().name}线程", "-> join前")
            job.join()
            Log.d("${Thread.currentThread().name}线程", "-> join后")
        }
    }

    private fun testCoroutineScope() {
        GlobalScope.launch(Dispatchers.Main) {
            Log.d("父协程上下文", "$coroutineContext")
            launch(CoroutineName("第一个子协程")) {
                Log.d("第一个子协程上下文", "$coroutineContext")
            }
            launch(Dispatchers.Unconfined) {
                Log.d("第二个子协程协程上下文", "$coroutineContext")
            }
        }
    }


    private fun testCoroutineScope2() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("exceptionHandler", "${coroutineContext[CoroutineName]} $throwable")
        }
        GlobalScope.launch(Dispatchers.Main + CoroutineName("scope1") + exceptionHandler) {
            Log.d("scope", "--------- 1")
            launch(CoroutineName("scope2") + exceptionHandler) {
                Log.d("scope", "--------- 2")
                throw  NullPointerException("空指针")
                Log.d("scope", "--------- 3")
            }
            val scope3 = launch(CoroutineName("scope3") + exceptionHandler) {
                Log.d("scope", "--------- 4")
                delay(2000)
                Log.d("scope", "--------- 5")
            }
            scope3.join()
            Log.d("scope", "--------- 6")
        }
    }


    private fun testCoroutineScope3() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("exceptionHandler", "${coroutineContext[CoroutineName]} $throwable")
        }
        GlobalScope.launch(Dispatchers.Main + CoroutineName("scope1") + exceptionHandler) {
            supervisorScope {
                Log.d("scope", "--------- 1")
                launch(CoroutineName("scope2") + exceptionHandler) {
                    Log.d("scope", "--------- 2")
                    throw  NullPointerException("空指针")
                    Log.d("scope", "--------- 3")
                    val scope3 = launch(CoroutineName("scope3") + exceptionHandler) {
                        Log.d("scope", "--------- 4")
                        delay(2000)
                        Log.d("scope", "--------- 5")
                    }
                    scope3.join()
                }
                val scope4 = launch(CoroutineName("scope4") + exceptionHandler) {
                    Log.d("scope", "--------- 6")
                    delay(2000)
                    Log.d("scope", "--------- 7")
                }
                scope4.join()
                Log.d("scope", "--------- 8")
            }
        }
    }

    private fun testCoroutineScope4() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("exceptionHandler", "${coroutineContext[CoroutineName]} $throwable")
        }
       val coroutineScope = CoroutineScope(SupervisorJob() + CoroutineName("coroutineScope"))
        GlobalScope.launch(Dispatchers.Main + CoroutineName("scope1") + exceptionHandler) {
            with(coroutineScope){
                val scope2 = launch(CoroutineName("scope2") + exceptionHandler) {
                    Log.d("scope", "1--------- ${coroutineContext[CoroutineName]}")
                    throw  NullPointerException("空指针")
                }
                val scope3 = launch(CoroutineName("scope3") + exceptionHandler) {
                    scope2.join()
                    Log.d("scope", "2--------- ${coroutineContext[CoroutineName]}")
                    delay(2000)
                    Log.d("scope", "3--------- ${coroutineContext[CoroutineName]}")
                }
                scope2.join()
                Log.d("scope", "4--------- ${coroutineContext[CoroutineName]}")
                coroutineScope.cancel()
                scope3.join()
                Log.d("scope", "5---------${coroutineContext[CoroutineName]}")
            }
            Log.d("scope", "6--------- ${coroutineContext[CoroutineName]}")
        }
    }

    private suspend fun test(){

    }

    private fun testException(){
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("exceptionHandler", "${coroutineContext[CoroutineName].toString()} 处理异常 ：*/$throwable")
        }
        GlobalScope.launch {

            coroutineScope {

            }
            supervisorScope {

            }

        }


      /*  GlobalScope.launch{
            launch(start = CoroutineStart.UNDISPATCHED) {
                Log.d("${Thread.currentThread().name}", " 我要开始抛异常了")
                try {
                    throw NullPointerException("异常测试")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            Log.d("${Thread.currentThread().name}", "end")
        }
       val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
           Log.d("exceptionHandler", "${coroutineContext[CoroutineName].toString()} 处理异常 ：$throwable")
       }
       GlobalScope.launch(CoroutineName("父协程") + exceptionHandler){
           val job = launch(CoroutineName("子协程")) {
               Log.d("${Thread.currentThread().name}","我要开始抛异常了" )
               throw NullPointerException("空指针异常")
           }

           for (index in 0..10){
               launch(CoroutineName("子协程$index")) {
                   Log.d("${Thread.currentThread().name}","${coroutineContext[CoroutineName]}" )
               }
           }

           try {
               job.join()
           } catch (e: Exception) {
               e.printStackTrace()
           }
           Log.d("${Thread.currentThread().name}", "end")
        } */
    }
}
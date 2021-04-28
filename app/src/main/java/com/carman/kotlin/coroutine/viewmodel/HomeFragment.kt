package com.carman.kotlin.coroutine.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.carman.kotlin.coroutine.R
import com.carman.kotlin.coroutine.extensions.delayMain
import com.carman.kotlin.coroutine.extensions.requestIO
import com.carman.kotlin.coroutine.extensions.requestMain

class HomeFragment:Fragment() {

    init {
        lifecycleScope.launchWhenCreated {
            Toast.makeText(context,"Fragment创建了", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestMain {
            //...
        }
        requestIO {
            //...
        }
        delayMain(100){
            //...
        }
    }
}
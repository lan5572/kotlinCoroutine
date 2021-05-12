package com.carman.kotlin.coroutine.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.carman.kotlin.coroutine.R
import com.carman.kotlin.coroutine.request.viewmodel.MainViewModel

class MainActivity05:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        defaultViewModelProviderFactory.create(MainViewModel::class.java)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.mUser.observe(this) {
            Log.d("mUser","$it")
        }
    }
}
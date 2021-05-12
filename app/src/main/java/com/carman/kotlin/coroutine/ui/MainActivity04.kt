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
import com.carman.kotlin.coroutine.base.BaseVBActivity
import com.carman.kotlin.coroutine.bean.Person
import com.carman.kotlin.coroutine.bean.Student
import com.carman.kotlin.coroutine.bean.Teacher
import com.carman.kotlin.coroutine.databinding.ActivityMain04Binding
import com.carman.kotlin.coroutine.databinding.ActivityMainBinding
import com.carman.kotlin.coroutine.extensions.*
import com.carman.kotlin.coroutine.interf.ItemClickListener
import com.carman.kotlin.coroutine.request.repository.MainRepository
import com.carman.kotlin.coroutine.request.viewmodel.MainViewModel
import com.carman.kotlin.coroutine.ui.adapter.HomeAdapter
import com.carman.kotlin.coroutine.ui.adapter.SecondAdapter
import kotlinx.coroutines.*

/**
 * 第四章节案例
 */
class MainActivity04 : BaseVBActivity<ActivityMain04Binding>() {

    override fun initObserve() {

    }
        lateinit var homeAdapter:HomeAdapter
        override fun ActivityMain04Binding.initBinding() {
//        homeAdapter = HomeAdapter(itemClickListener)
//        with(recyclerView){
//            layoutManager = LinearLayoutManager(this@MainActivity).apply {
//                orientation = RecyclerView.VERTICAL
//            }
//            adapter = homeAdapter
//        }
//        homeAdapter.setData(listOf("a","b","c","d","e","f"))
//        btn.setOnClickListener {
//            homeAdapter.setData(listOf("c","d","e","f"))
//        }

        val secondAdapter = SecondAdapter()
                with(recyclerView){
            layoutManager = LinearLayoutManager(this@MainActivity04).apply {
                orientation = RecyclerView.VERTICAL
            }
            adapter = secondAdapter
        }
        secondAdapter.setData(
                listOf(
                        Teacher(1,"Person","语文"),
                        Student(2,"Person","一年级"),
                        Teacher(3,"Person","数学"),
                ))
    }


    private val itemClickListener = object : ItemClickListener<String> {
        override fun onItemClick(view: View, position: Int, data: String) {
            Log.d("onItemClick", "data:$data   position:${homeAdapter.getActualPosition(data)}")
        }
    }
}
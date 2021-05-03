package com.carman.kotlin.coroutine.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.carman.kotlin.coroutine.R
import com.carman.kotlin.coroutine.base.BaseFragment
import com.carman.kotlin.coroutine.databinding.FragmentMainBinding
import com.carman.kotlin.coroutine.extensions.delayMain
import com.carman.kotlin.coroutine.extensions.requestIO
import com.carman.kotlin.coroutine.extensions.requestMain

class HomeFragment:BaseFragment<FragmentMainBinding>() {

    override fun FragmentMainBinding.initBinding() {

    }
}
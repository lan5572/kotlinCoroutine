package com.carman.kotlin.coroutine.interf

import android.view.View
import androidx.databinding.BindingAdapter
import com.carman.kotlin.coroutine.interf.DoubleClickHelper


@BindingAdapter("bindOnClick")
fun bindOnClick(view: View?, listener: View.OnClickListener?) {
    DoubleClickHelper.click(view, listener)
}
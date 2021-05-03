package com.carman.kotlin.coroutine.ui.adapter

import com.carman.kotlin.coroutine.base.BaseAdapter
import com.carman.kotlin.coroutine.databinding.ItemHomeBinding
import com.carman.kotlin.coroutine.interf.ItemClickListener


class HomeAdapter(private val listener: ItemClickListener<String>) : BaseAdapter<String, ItemHomeBinding>() {

    override fun ItemHomeBinding.setListener() {
      itemClickListener = listener
    }

    override fun ItemHomeBinding.onBindViewHolder(bean: String, position: Int) {
        this.bean = bean
        this.position = position
        tv.text = bean
    }
}
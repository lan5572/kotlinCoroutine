package com.carman.kotlin.coroutine.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.carman.kotlin.coroutine.extensions.getViewBinding
import java.util.*

/**
 *
 *@author carman
 * @time 2021-4-16 13:25
 */


abstract class BaseAdapter<T, VB : ViewDataBinding> : RecyclerView.Adapter<BaseAdapter.BindViewHolder<VB>>() {

    private var mData: List<T> = mutableListOf()

    fun setData(data: List<T>?) {
        data?.let {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return mData.size
                }

                override fun getNewListSize(): Int {
                    return it.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldData: T = mData[oldItemPosition]
                    val newData: T = it[newItemPosition]
                    return this@BaseAdapter.areItemsTheSame(oldData, newData)
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldData: T = mData[oldItemPosition]
                    val newData: T = it[newItemPosition]
                    return this@BaseAdapter.areItemContentsTheSame(oldData, newData, oldItemPosition, newItemPosition)
                }
            })
            mData = data
            result.dispatchUpdatesTo(this)
        } ?: let {
            mData = mutableListOf()
            notifyItemRangeChanged(0, mData.size)
        }

    }

    fun addData(data: List<T>?, position: Int? = null) {
        if (!data.isNullOrEmpty()) {
            with(LinkedList(mData)){
                position?.let {
                    val startPosition = when {
                        it < 0 -> 0
                        it >= size -> size
                        else -> it
                    }
                    addAll(startPosition, data)
                }?: addAll(data)
                setData(this)
            }
        }
    }

    protected open fun areItemContentsTheSame(oldItem: T, newItem: T, oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem == newItem
    }

    protected open fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    fun getData(): List<T> {
        return mData
    }

    fun getItem(position: Int): T {
        return mData[position]
    }

    fun getActualPosition(data: T): Int {
        return mData.indexOf(data)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindViewHolder<VB> {
        return with(getViewBinding<VB>(LayoutInflater.from(parent.context), parent,1)) {
            setListener()
            BindViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: BindViewHolder<VB>, position: Int) {
        with(holder.binding){
           onBindViewHolder(getItem(position), position)
           executePendingBindings()
        }
    }

    open fun VB.setListener() {}

    abstract fun VB.onBindViewHolder(bean: T, position: Int)

    class BindViewHolder<M : ViewDataBinding>(var binding: M) :
            RecyclerView.ViewHolder(binding.root)
}
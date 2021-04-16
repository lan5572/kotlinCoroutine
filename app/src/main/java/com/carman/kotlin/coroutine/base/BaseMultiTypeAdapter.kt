package com.carman.kotlin.coroutine.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 *
 *@author carman
 * @time 2021-4-16 13:25
 */
abstract class BaseMultiTypeAdapter<T> : RecyclerView.Adapter<BaseMultiTypeAdapter.MultiTypeViewHolder>() {


    private val diffCallback = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return this@BaseMultiTypeAdapter.areItemsTheSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return this@BaseMultiTypeAdapter.areItemContentsTheSame(oldItem, newItem)
        }
    }

    private val differ = AsyncListDiffer<T>(this, diffCallback)

    fun setData(data: List<T>?) {
        differ.submitList(LinkedList(data))
    }

    fun addData(data: List<T>?, position: Int? = null) {
        if (!data.isNullOrEmpty()) {
            val newData = LinkedList(differ.currentList)
            if (position != null) {
                val startPosition = when {
                    position < 0 -> 0
                    position >= newData.size -> newData.size
                    else -> position
                }
                newData.addAll(startPosition, data)
                differ.submitList(newData)
            } else {
                newData.addAll(data)
                differ.submitList(newData)
            }
        }
    }

    protected open fun areItemContentsTheSame(oldData: T, newData: T): Boolean {
        return oldData === newData
    }

    protected open fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    fun getData(): List<T> {
        return differ.currentList
    }

    fun getItem(position: Int): T {
        return differ.currentList[position]
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiTypeViewHolder {
        return MultiTypeViewHolder(getBinding(parent,viewType))
    }

    override fun onBindViewHolder(holder: MultiTypeViewHolder, position: Int) {
        holder.onBindViewHolder(holder,getItem(position),position)
        holder.binding.executePendingBindings()
    }

    abstract fun MultiTypeViewHolder.onBindViewHolder(holder: MultiTypeViewHolder, item:T, position: Int)

    abstract fun getBinding(parent: ViewGroup, viewType: Int):ViewDataBinding

    protected open fun <K:ViewDataBinding> loadLayout(parent: ViewGroup, @LayoutRes layoutId: Int): K {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
    }

    class MultiTypeViewHolder(var binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)
}
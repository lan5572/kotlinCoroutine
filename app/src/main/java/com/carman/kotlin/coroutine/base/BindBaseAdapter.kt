package com.qmnl.pati.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.ParameterizedType
import java.util.*

/**
 *
 *@author carman
 * @time 2021-4-16 13:25
 */
class BindViewHolder<M: ViewDataBinding>(var binding: M) :
    RecyclerView.ViewHolder(binding.root)

abstract class BindBaseAdapter<T, VB : ViewDataBinding>: RecyclerView.Adapter<BindViewHolder<VB>>() {

    private var mData: List<T> = mutableListOf()

    fun setData(data: List<T>?) {
        data?.let {
             val result =  DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                 override fun getOldListSize(): Int {
                     return mData.size
                 }

                 override fun getNewListSize(): Int {
                     return it.size
                 }

                 override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                     val oldData: T = mData[oldItemPosition]
                     val newData: T =  it[newItemPosition]
                     return this@BindBaseAdapter.areItemsTheSame(oldData, newData)
                 }

                 override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                     val oldData: T = mData[oldItemPosition]
                     val newData: T =  it[newItemPosition]
                     return this@BindBaseAdapter.areItemContentsTheSame(oldData, newData,oldItemPosition, newItemPosition)
                 }
             })
            mData = data
             result.dispatchUpdatesTo(this)
        }?:let{
            mData = mutableListOf()
            notifyItemRangeChanged(0, mData.size)
        }

    }

    fun addData(data: List<T>?, position: Int? = null) {
        if (!data.isNullOrEmpty()) {
            val newData = LinkedList(mData)
            if (position != null) {
                val startPosition = when {
                    position < 0 -> 0
                    position >= newData.size -> newData.size
                    else -> position
                }
                newData.addAll(startPosition, data)
               setData(newData)
            } else {
                newData.addAll(data)
                setData(newData)
            }
        }
    }

    protected open fun areItemContentsTheSame(oldItem: T, newItem: T,oldItemPosition: Int, newItemPosition: Int): Boolean {
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

    fun getPosition(data:T):Int {
        return mData.indexOf(data)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindViewHolder<VB> {
        val vbClass: Class<VB> = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VB>
        val inflate = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        val binding  = inflate.invoke(null, LayoutInflater.from(parent.context), parent, false) as VB
        binding.setListener()
        return BindViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindViewHolder<VB>, position: Int) {
        holder.binding.onBindViewHolder(getItem(position), position)
        holder.binding.executePendingBindings()
    }

    open fun VB.setListener(){}

    abstract fun VB.onBindViewHolder(bean: T, position: Int)

}
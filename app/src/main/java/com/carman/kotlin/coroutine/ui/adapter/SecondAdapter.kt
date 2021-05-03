package com.carman.kotlin.coroutine.ui.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.carman.kotlin.coroutine.base.BaseMultiTypeAdapter
import com.carman.kotlin.coroutine.bean.Person
import com.carman.kotlin.coroutine.bean.Student
import com.carman.kotlin.coroutine.bean.Teacher
import com.carman.kotlin.coroutine.databinding.ItemPersionBinding
import com.carman.kotlin.coroutine.databinding.ItemStudentBinding
import com.carman.kotlin.coroutine.databinding.ItemTeacherBinding

class SecondAdapter: BaseMultiTypeAdapter<Person>() {

    companion object{
        private const val ITEM_DEFAULT_TYPE = 0
        private const val ITEM_STUDENT_TYPE = 1
        private const val ITEM_TEACHER_TYPE = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is Student -> ITEM_STUDENT_TYPE
            is Teacher -> ITEM_TEACHER_TYPE
            else -> ITEM_DEFAULT_TYPE
        }
    }

    override fun onCreateMultiViewHolder(parent: ViewGroup, viewType: Int): ViewDataBinding {
      return when(viewType){
          ITEM_STUDENT_TYPE -> loadLayout(ItemStudentBinding::class.java,parent)
          ITEM_TEACHER_TYPE ->  loadLayout(ItemTeacherBinding::class.java,parent)
           else ->  loadLayout(ItemPersionBinding::class.java,parent)
       }
    }

    override fun MultiTypeViewHolder.onBindViewHolder(holder: MultiTypeViewHolder, item: Person, position: Int) {
        when(holder.binding){
            is ItemStudentBinding ->{
                Log.d("ItemStudentBinding","item : $item   position : $position")
            }
            is ItemTeacherBinding ->{
                Log.d("ItemTeacherBinding","item : $item   position : $position")
            }
        }
    }

    override fun areItemContentsTheSame(oldItem: Person, newItem: Person, oldItemPosition: Int, newItemPosition: Int): Boolean {
        return super.areItemContentsTheSame(oldItem, newItem, oldItemPosition, newItemPosition)
    }

    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return super.areItemsTheSame(oldItem, newItem)
    }
}
package com.carman.kotlin.coroutine.bean


sealed class Person(open val id :Int, open val name:String)

data class Student(
        override val id:Int,
        override val name:String,
        val grade:String):Person(id, name)

data class Teacher(
        override val id:Int,
        override val name:String,
        val subject:String):Person(id, name)
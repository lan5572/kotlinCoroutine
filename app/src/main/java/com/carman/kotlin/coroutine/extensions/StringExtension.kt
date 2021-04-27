package com.carman.kotlin.coroutine.extensions

import java.nio.charset.Charset
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.toUtf8Bytes():ByteArray{
    return  this.toByteArray(Charset.forName("UTF-8"))
}

fun String.toSBC(): String {
    val c = this.toCharArray()
    for (i in c.indices) {
        if (c[i] == ' ') {
            c[i] = '\u3000'
        } else if (c[i] < '\u007f') {
            c[i] = (c[i] + 65248)
        }
    }
    return String(c)
}

inline fun String.replaceBlank(): String {
    val p: Pattern = Pattern.compile("\\s*|\t|\r|\n")
    val m: Matcher = p.matcher(this)
    return m.replaceAll("")
}

infix fun Int.toList(x:Int): Array<Int> {
    var array = mutableListOf<Int>()
    for (index in this..x){
        array = (array + index) as MutableList<Int>
    }
    return array.toTypedArray()
}

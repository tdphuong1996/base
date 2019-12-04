package com.ekingdom.common.extension

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import java.security.SecureRandom

fun Any.getClassTag(): String = this.javaClass.name
fun Any.getClassSimpleTag(): String = this.javaClass.simpleName

@Suppress("NOTHING_TO_INLINE")
inline fun Any.getMethodTag(): String =
    getClassTag() + "::" + object : Any() {}.javaClass.enclosingMethod?.name

inline fun Any.getSessionID(): String {
    val AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
    val rnd = SecureRandom()
    val len = 16

    val sb = StringBuilder(len)
    for (i in 0 until len) sb.append(AB[rnd.nextInt(AB.length)])
    return sb.toString()
}


//Khieu ne
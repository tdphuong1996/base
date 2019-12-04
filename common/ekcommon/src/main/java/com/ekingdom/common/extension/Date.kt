package com.ekingdom.common.extension

import java.text.SimpleDateFormat
import java.util.*


fun Date.toString(format: String = "yyyy-MM-dd HH:mm:ss"): String {
    return try {
        SimpleDateFormat(format, Locale.getDefault()).format(date)
    } catch (e: Exception) {
        ""
    }
}
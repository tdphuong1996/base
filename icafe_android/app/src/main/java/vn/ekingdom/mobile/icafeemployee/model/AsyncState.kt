package vn.ekingdom.mobile.icafeemployee.model

import android.os.Build
import androidx.annotation.RequiresApi
import vn.ekingdom.mobile.icafeemployee.R

sealed class AsyncState {
    object Started : AsyncState()
    object Completed : AsyncState()
    class Failed(val error: Throwable) : AsyncState()
}
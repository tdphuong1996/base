package com.ekingdom.common.extension

import android.content.Context
import androidx.annotation.Nullable
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.ekingdom.common.view.EKMessageDialogFragment

fun Context.getGenericDialog(titleResID: Int, message: Int): DialogFragment {
    return EKMessageDialogFragment.newInstance(titleResID, message)
}

/**
 * Show dialog, kiểm tra dialog đã hiện thì không hiện nữa
 */
fun DialogFragment.showIfNonExistent(manager: FragmentManager, tag: String) {
    if (manager.findFragmentByTag(tag) == null)
        this.show(manager, tag)
}

fun DialogFragment.showNowIfNonExistent(manager: FragmentManager, tag: String) {
    if (manager.findFragmentByTag(tag) == null)
        this.showNow(manager, tag)
}
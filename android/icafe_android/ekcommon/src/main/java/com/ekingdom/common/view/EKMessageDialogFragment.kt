package com.ekingdom.common.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.ekingdom.ekcommon.R

class EKMessageDialogFragment : DialogFragment() {

    companion object {
        @JvmStatic
        fun newInstance(titleResId: Int, messageResId: Int, clickCallback: ((Boolean) -> Unit)? = null): EKMessageDialogFragment {
            return EKMessageDialogFragment().apply {
                this.title = titleResId
                this.message = messageResId
                this.clickCallback = clickCallback
            }
        }
    }

    var title: Int = R.string.app_name //test
    var message: Int = R.string.app_name // test
    var clickCallback: ((Boolean) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog = AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()
        return dialog
    }
}
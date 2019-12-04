package com.ekingdom.common.extension

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.ThreeBounce

fun ViewGroup.showLoading(isShow: Boolean) {
//    val rootView: ViewGroup =
//        getWindow().getDecorView().findViewById(R.id.content)
    if (findViewWithTag<View?>("Loading") == null) {
        val relativeLayout = RelativeLayout(context)
        relativeLayout.gravity = Gravity.CENTER
        relativeLayout.setOnClickListener { v: View? -> }
        relativeLayout.tag = "Loading"
        relativeLayout.setBackgroundColor(Color.parseColor("#50000000"))
        val progressBar = ProgressBar(context)
        val sprite: Sprite = ThreeBounce()
//        sprite.setColor(resources.getColor(R.color.colorActive))
        progressBar.indeterminateDrawable = sprite
        val layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.addRule(Gravity.CENTER)
        relativeLayout.addView(progressBar, layoutParams)
        (this as ViewGroup).addView(
            relativeLayout,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
    }

    findViewWithTag<View>("Loading").visibility = if (isShow) View.VISIBLE else View.GONE
}

fun View?.showKeyboard () {
    if (this == null) {
        return
    }
    if (requestFocus()) {
        val imm =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, 0)
    }
}

fun View?.hideKeyboard () {
    if (this == null) {
        return
    }
    val imm =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (!imm.isActive) {
        return
    }
    imm.hideSoftInputFromWindow(windowToken, 0)
    clearFocus()
}
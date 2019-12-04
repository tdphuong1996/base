package vn.ekingdom.mobile.icafeemployee.base

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.RelativeLayout
import com.ekingdom.common.extension.hideKeyboard
import com.ekingdom.common.extension.showKeyboard
import vn.ekingdom.mobile.icafeemployee.R
import kotlinx.android.synthetic.main.search_view_layout.view.*

class SearchViewToolbar(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    var iSearchShow: ((Boolean) -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.search_view_layout, this, true)
        imgRight.setOnClickListener { openSearch() }
        imgClose.setOnClickListener { closeSearch() }
    }

    private fun openSearch() {
        edtSearch.setText("")
        rllSearchOpen.visibility = View.VISIBLE
        val circularReveal = ViewAnimationUtils.createCircularReveal(
            rllSearchOpen,
            (imgRight.right + imgRight.left) / 2,
            (imgRight.top + imgRight.bottom) / 2,
            0f, width.toFloat()
        )
        circularReveal.duration = 300
        circularReveal.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) = Unit
            override fun onAnimationCancel(animation: Animator?) = Unit
            override fun onAnimationStart(animation: Animator?) {
                iSearchShow?.invoke(true)
            }
            override fun onAnimationEnd(animation: Animator?) {
                edtSearch.requestFocus()
                edtSearch.showKeyboard()
            }
        })
        circularReveal.start()
    }

    private fun closeSearch() {
        val circularConceal = ViewAnimationUtils.createCircularReveal(
            rllSearchOpen,
            (imgRight.right + imgRight.left) / 2,
            (imgRight.top + imgRight.bottom) / 2,
            width.toFloat(), 0f
        )
        edtSearch.hideKeyboard()
        circularConceal.duration = 300
        circularConceal.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) = Unit
            override fun onAnimationCancel(animation: Animator?) = Unit
            override fun onAnimationStart(animation: Animator?) {
                iSearchShow?.invoke(false)
            }
            override fun onAnimationEnd(animation: Animator?) {
                rllSearchOpen.visibility = View.INVISIBLE
                edtSearch.setText("")
                circularConceal.removeAllListeners()
            }
        })
        circularConceal.start()
    }
}
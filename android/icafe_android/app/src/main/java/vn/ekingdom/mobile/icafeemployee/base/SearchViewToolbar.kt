package vn.ekingdom.mobile.icafeemployee.base

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.view.ViewCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import com.ekingdom.common.extension.hideKeyboard
import com.ekingdom.common.extension.showKeyboard
import com.ekingdom.common.view.EKToolbar
import kotlinx.android.synthetic.main.activity_main.*
import vn.ekingdom.mobile.icafeemployee.R
import kotlinx.android.synthetic.main.search_view_layout.view.*

class SearchViewToolbar(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs),
    EKToolbar.OnToolbarActionClickListener {

    var iSearchShow: ((Boolean) -> Unit)? = null
    private var isAnimationRunning: Boolean = false

    init {
        LayoutInflater.from(context).inflate(R.layout.search_view_layout, this, true)
        ekToolbar.setOnToolbarActionClickListener(this)
        imgClose.setOnClickListener { closeSearch() }
        imgBackSearch.setOnClickListener { closeSearch() }
        ekToolbar.getImageBack().setImageResource(R.drawable.ic_icscanqr)
        setTitle("Dashboard")

        ViewCompat.setOnApplyWindowInsetsListener(ekToolbar) { view, insets ->
            (ekToolbar as View).setPaddingTop(insets.systemWindowInsetTop)
            (rllSearchOpen as View).setPaddingTop(insets.systemWindowInsetTop)
            insets
        }
    }

    private fun openSearch() {
        if (isAnimationRunning)
            return
        edtSearch.setText("")
        rllSearchOpen.visibility = View.VISIBLE
        val circularReveal = ViewAnimationUtils.createCircularReveal(
            rllSearchOpen,
            (imgClose.right + imgClose.left) / 2,
            (imgClose.top + imgClose.bottom) / 2,
            0f, width.toFloat()
        )
        circularReveal.duration = 300
        circularReveal.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) = Unit
            override fun onAnimationCancel(animation: Animator?) = Unit
            override fun onAnimationStart(animation: Animator?) {
                isAnimationRunning = true
                iSearchShow?.invoke(true)
            }
            override fun onAnimationEnd(animation: Animator?) {
                edtSearch.requestFocus()
                edtSearch.showKeyboard()
                isAnimationRunning = false
            }
        })
        circularReveal.start()
    }

    fun closeSearch() {
        if (isAnimationRunning)
            return
        val circularConceal = ViewAnimationUtils.createCircularReveal(
            rllSearchOpen,
            (imgClose.right + imgClose.left) / 2,
            (imgClose.top + imgClose.bottom) / 2,
            width.toFloat(), 0f
        )
        edtSearch.hideKeyboard()
        circularConceal.duration = 300
        circularConceal.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) = Unit
            override fun onAnimationCancel(animation: Animator?) = Unit
            override fun onAnimationStart(animation: Animator?) {
                isAnimationRunning = true
                iSearchShow?.invoke(false)
            }
            override fun onAnimationEnd(animation: Animator?) {
                rllSearchOpen.visibility = View.INVISIBLE
                edtSearch.setText("")
                circularConceal.removeAllListeners()
                isAnimationRunning = false
            }
        })
        circularConceal.start()
    }

    fun setTitle(title: String) {
        ekToolbar.setTitle(title)
    }

    fun isSearchShowing() = rllSearchOpen.visibility == View.VISIBLE

    override fun onClickBack(view: View?) {
//        closeSearch()
    }

    override fun onClickBaseActionItem(view: View?) {
        openSearch()
    }

    override fun onClickMoreItem(view: View?) {
    }

    private fun View.setPaddingTop(value: Int) = updateLayoutParams<ViewGroup.MarginLayoutParams> {
        setPadding(paddingLeft, value, paddingRight, paddingBottom)
        height = pxFromDp(56f) + value
    }

    private fun pxFromDp(dp: Float): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}
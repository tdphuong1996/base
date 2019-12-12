package com.ekingdom.common.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.updateLayoutParams
import com.ekingdom.ekcommon.R
import kotlinx.android.synthetic.main.base_toolbar_item.view.*

class EKToolbar (context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    private var baseActionIconResource: Drawable? = null
    private var isShowBaseActionIcon = false
    private var isShowMoreIcon = false
    private var title = ""
    private var onToolbarActionClickListener: OnToolbarActionClickListener? = null

    init {
        init()
        val typedArray =
            context!!.obtainStyledAttributes(attrs, R.styleable.EKToolbar, 0, 0)
        try {
            baseActionIconResource =
                typedArray.getDrawable(R.styleable.EKToolbar_baseActionItemIcon)
            isShowBaseActionIcon = typedArray.getBoolean(
                R.styleable.EKToolbar_showBaseActionItem,
                isShowBaseActionIcon
            )
            isShowMoreIcon =
                typedArray.getBoolean(R.styleable.EKToolbar_showMoreItem, isShowMoreIcon)
            title = typedArray.getString(R.styleable.EKToolbar_title)!!
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "EKToolbar: " + e.message)
        } finally {
            typedArray.recycle()
        }

        if (baseActionIconResource != null) {
            imgAction.setImageDrawable(baseActionIconResource)
        }
        groupBase!!.visibility = if (isShowBaseActionIcon) View.VISIBLE else View.GONE
        groupMore!!.visibility = if (isShowMoreIcon) View.VISIBLE else View.GONE
        tvTitle!!.text = title
    }

    private fun init() {
        tag = "Toolbar"
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            view.setPaddingTop(insets.systemWindowInsetTop)
            insets
        }
        View.inflate(context, R.layout.base_toolbar_item, this)
        clBack.setOnClickListener { v ->
            if (onToolbarActionClickListener != null) onToolbarActionClickListener?.onClickBack(v)
        }
        tvAction.setOnClickListener { v ->
            if (onToolbarActionClickListener != null) onToolbarActionClickListener?.onClickBaseActionItem(v)
        }
        tvMore.setOnClickListener { v ->
            if (onToolbarActionClickListener != null) onToolbarActionClickListener?.onClickMoreItem(v)
        }
    }

    fun setShowBaseActionIcon(isShow: Boolean) {
        isShowBaseActionIcon = isShow
        groupBase.visibility = if (isShowBaseActionIcon) View.VISIBLE else View.GONE
    }

    fun setBaseImageIcon(drawableID: Int) {
        imgAction.setImageResource(drawableID)
    }

    fun setTitle(title: String?) {
        tvTitle.text = title
    }

    fun setOnToolbarActionClickListener(onToolbarActionClickListener: OnToolbarActionClickListener?) {
        this.onToolbarActionClickListener = onToolbarActionClickListener
    }

    private fun View.setPaddingTop(value: Int) = updateLayoutParams<ViewGroup.MarginLayoutParams> {
        setPadding(paddingLeft, value, paddingRight, paddingBottom)
        height = pxFromDp(56f) + value
    }

    private fun pxFromDp(dp: Float): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    fun getImageAction() = imgAction
    fun getImageBack() = imgBack

    interface OnToolbarActionClickListener {
        fun onClickBack(view: View?)
        fun onClickBaseActionItem(view: View?)
        fun onClickMoreItem(view: View?)
    }
}
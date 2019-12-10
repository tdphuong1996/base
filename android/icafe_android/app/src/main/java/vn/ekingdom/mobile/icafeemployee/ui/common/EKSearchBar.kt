package vn.ekingdom.mobile.icafeemployee.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import vn.ekingdom.mobile.icafeemployee.R

class EKSearchBar(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.view_search_bar, this)
    }
}
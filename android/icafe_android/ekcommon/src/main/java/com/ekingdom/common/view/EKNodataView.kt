package com.ekingdom.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ekingdom.ekcommon.R

class EKNodataView(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        View.inflate(context, R.layout.ek_nodataview, this)
    }

}
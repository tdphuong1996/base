package com.ekingdom.common.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ekingdom.ekcommon.R
import kotlinx.android.synthetic.main.ek_recyclerview.view.*

class EKRecyclerVIew(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?){
        View.inflate(context, R.layout.ek_recyclerview, this)
    }

    fun getRcvData() = rcvData

    fun noData(boolean: Boolean) {
        vNodata.visibility = if (boolean) View.VISIBLE else View.GONE
        rcvData.visibility = if (boolean) View.GONE else View.VISIBLE
    }

}
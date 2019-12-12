package com.ekingdom.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ekingdom.common.base.BaseAdapter
import com.ekingdom.ekcommon.R
import kotlinx.android.synthetic.main.ek_recyclerview.view.*

class EKRecyclerVIew(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    lateinit var mAdapter: BaseAdapter<*, *>

    private var page = 0
    public var maxPage: Int = 0
    private var isLoading: Boolean = false
    private val mThresholdLoadMore = 3

     private var onRefreshListener: SwipeRefreshLayout.OnRefreshListener?=null

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        View.inflate(context, R.layout.ek_recyclerview, this)
        swlRefresh.apply {
          setOnRefreshListener {
              isRefreshing=false
              onRefreshListener?.onRefresh()
          }
        }

    }


    fun initLoadMore(listener: LoadMoreListener?) {
        if (listener == null) return
        rvData.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var mLayoutManager: LinearLayoutManager =
                ((rvData.layoutManager as LinearLayoutManager?)!!)
            var lastVisibleItems: Int = 0
            var totalItemCount: Int = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                totalItemCount = mLayoutManager.itemCount
                lastVisibleItems = mLayoutManager.findLastVisibleItemPosition()
                if (totalItemCount <= lastVisibleItems + mThresholdLoadMore) {
                    if (!isLoading && page < maxPage) {
                        page++
                        mAdapter.arrData.add(null)
                        handler.post { mAdapter.notifyItemInserted(mAdapter.arrData.count() - 1) }
                        listener.onLoadMore()
                        isLoading = true
                    } else
                        handler.removeCallbacksAndMessages(null)
                }

            }
        })
    }

    fun setUpAdapter(baseAdapter: BaseAdapter<*, *>) {
        rvData.layoutManager = LinearLayoutManager(context)

        this.mAdapter = baseAdapter
        rvData.adapter = this.mAdapter
    }


    fun setUpAdapter(baseAdapter: BaseAdapter<*, *>, layoutManager: LinearLayoutManager) {
        rvData.layoutManager = layoutManager
        rvData.isNestedScrollingEnabled = false

        this.mAdapter = baseAdapter
    }

    fun noData(boolean: Boolean) {
        vNodata.visibility = if (boolean) View.VISIBLE else View.GONE
        rvData.visibility = if (boolean) View.GONE else View.VISIBLE
    }

    fun handleDataResponse(maxPage: Int) {
        if (isLoading && mAdapter.arrData.count() > 0 && mAdapter.arrData[mAdapter.arrData.count() - 1] == null) {
            mAdapter.arrData.removeAt(mAdapter.arrData.count() - 1)
            isLoading = false
            mAdapter.notifyItemRemoved(mAdapter.arrData.count())
        }
        this.maxPage = maxPage
    }


    fun setRefreshListener(listener: SwipeRefreshLayout.OnRefreshListener) {
        this.onRefreshListener = listener
    }

    interface LoadMoreListener {
        fun onLoadMore()
    }

}
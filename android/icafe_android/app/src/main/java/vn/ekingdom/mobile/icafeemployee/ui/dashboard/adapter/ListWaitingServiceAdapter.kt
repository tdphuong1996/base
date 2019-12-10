package vn.ekingdom.mobile.icafeemployee.ui.dashboard.adapter

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekingdom.common.base.BaseAdapter
import com.ekingdom.common.base.BaseViewHolder
import com.ekingdom.common.extension.toString
import kotlinx.android.synthetic.main.rcv_item_waiting_services.view.*
import vn.ekingdom.mobile.icafeemployee.model.Order
import java.util.*

class ListWaitingServiceAdapter: BaseAdapter<Order, ListWaitingServicesViewHolder> {
    constructor(layoutRes: Int, _arrData: MutableList<Order>) : super(layoutRes, _arrData)

    override fun getViewHolder(viewType: Int, parent: ViewGroup): ListWaitingServicesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return ListWaitingServicesViewHolder(view)
    }
}

class ListWaitingServicesViewHolder(containerView: View) : BaseViewHolder<Order>(containerView) {

    override fun onDataChanged() {
        val context = containerView.context
        containerView.apply {
            tvOrderID.text = "ORD-${data?.orderId}"
            tvComputerName.text = "Máy ${data?.workStationId}"
            tvOrderTime.text = data?.orderDate?.toString("dd/MM/yyyy hh:mm")
            tvTimeAgo.text = DateUtils.getRelativeTimeSpanString(data?.orderDate?.time ?: Date().time, Calendar.getInstance().timeInMillis, DateUtils.MINUTE_IN_MILLIS)
        }
    }

}
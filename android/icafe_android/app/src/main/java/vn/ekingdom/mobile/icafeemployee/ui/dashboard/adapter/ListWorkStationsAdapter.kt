package vn.ekingdom.mobile.icafeemployee.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekingdom.common.base.BaseAdapter
import com.ekingdom.common.base.BaseViewHolder
import kotlinx.android.synthetic.main.rcv_item_ws_status.view.*
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.model.WSStatus
import vn.ekingdom.mobile.icafeemployee.model.WorkStationModel

class ListWorkStationsAdapter(layoutRes: Int, _arrData: MutableList<WorkStationModel>) :
    BaseAdapter<WorkStationModel, ListWorkStationsViewHolder>(layoutRes, _arrData) {
    var clickAction: ((WorkStationModel?, Int) -> Unit)? = null

    override fun getViewHolder(viewType: Int, parent: ViewGroup): ListWorkStationsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        val listWorkStationsViewHolder = ListWorkStationsViewHolder(view)
        view.setOnClickListener { clickAction?.invoke(listWorkStationsViewHolder.data, listWorkStationsViewHolder.adapterPosition) }
        return listWorkStationsViewHolder
    }
}

class ListWorkStationsViewHolder(containerView: View) :
    BaseViewHolder<WorkStationModel>(containerView) {
    override fun onDataChanged() {
        val context = containerView.context
        var colorStatus = context.getColor(R.color.colorWSReady)
        var statusName = context.getString(R.string.ws_ready)
        var icon = R.drawable.ic_icstatuspc_normal
        when(data?.status) {
            WSStatus.A.status -> {

            }
            WSStatus.U.status -> {
                colorStatus = containerView.context.getColor(R.color.colorWSUsed)
                statusName = context.getString(R.string.ws_used)
                icon = R.drawable.ic_icstatuspc_connected
            }
            WSStatus.M.status -> {
                colorStatus = containerView.context.getColor(R.color.colorWSMaintenance)
                statusName = context.getString(R.string.ws_maintenance)
                icon = R.drawable.ic_icstatuspc_warning
            }
            WSStatus.E.status -> {
                colorStatus = containerView.context.getColor(R.color.colorWSError)
                statusName = context.getString(R.string.ws_error)
                icon = R.drawable.ic_icstatuspc_error
            }
        }

        containerView.apply {
            tvTotalPC.text = data?.workStationName
            tvStatus.text = statusName
            tvStatus.setTextColor(colorStatus)
            imgPC.setImageResource(icon)
        }

    }

}
package vn.ekingdom.mobile.icafeemployee.ui.dashboard

import android.app.ActionBar
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.ekingdom.mobile.icafeemployee.MainActivity
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.model.Order
import vn.ekingdom.mobile.icafeemployee.model.WorkStationModel
import vn.ekingdom.mobile.icafeemployee.ui.common.BaseFragment
import vn.ekingdom.mobile.icafeemployee.ui.dashboard.adapter.ListWaitingServiceAdapter
import vn.ekingdom.mobile.icafeemployee.ui.dashboard.adapter.ListWorkStationsAdapter
import java.util.*

class DashBoardFragment : BaseFragment<DashBoardViewModel>() {
    override val viewModel by viewModel<DashBoardViewModel>()
    override val layoutResID: Int
        get() = R.layout.fragment_dashboard

    private lateinit var listWorkStationsAdapter: ListWorkStationsAdapter
    private lateinit var listWaitingServiceAdapter: ListWaitingServiceAdapter

    override fun init() {
        (activity as? MainActivity)?.setToolbarTitle(getString(R.string.dashboard))
    }

    override fun initView(view: View) {
        initRCVWorkStations(view)
        initRCVServices(view)
    }

    override fun getData() {

    }

    private fun initRCVWorkStations(view: View) {
        listWorkStationsAdapter = ListWorkStationsAdapter(R.layout.rcv_item_ws_status, getWSList()).apply {
            clickAction = { wsModel, position ->
                (activity as? MainActivity)?.navController?.navigate(R.id.action_home_to_ws_list)
            }
        }
        view.ekWSStatus.apply {
            removeDivider()
            layoutParams.apply { height = ViewGroup.LayoutParams.WRAP_CONTENT }
        }
        view.ekWSStatus.getRcvData().apply {
            layoutParams.apply { height = ViewGroup.LayoutParams.WRAP_CONTENT }
            isNestedScrollingEnabled = false
            layoutManager = GridLayoutManager(context, 2)
            adapter = listWorkStationsAdapter
        }
    }

    private fun initRCVServices(view: View) {
        listWaitingServiceAdapter = ListWaitingServiceAdapter(R.layout.rcv_item_waiting_services, getServicesList())
        view.ekWaitingOrder.getRcvData().apply {
            layoutParams.apply { height = ViewGroup.LayoutParams.WRAP_CONTENT }
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            adapter = listWaitingServiceAdapter
        }
    }

    private fun getWSList(): MutableList<WorkStationModel> {
        return arrayListOf(
            WorkStationModel(workStationName = "36 máy", status = "A"),
            WorkStationModel(workStationName = "50 máy", status = "U"),
            WorkStationModel(workStationName = "4 máy", status = "M"),
            WorkStationModel(workStationName = "10 máy", status = "E")
        )
    }
    private fun getServicesList(): MutableList<Order> {
        return arrayListOf(
            Order(orderId = "1", workStationId = 1, orderDate = Calendar.getInstance().time),
            Order(orderId = "2", workStationId = 2, orderDate = Calendar.getInstance().time),
            Order(orderId = "3", workStationId = 3, orderDate = Calendar.getInstance().time),
            Order(orderId = "4", workStationId = 4, orderDate = Calendar.getInstance().time),
            Order(orderId = "5", workStationId = 5, orderDate = Calendar.getInstance().time)
        )
    }
}
package vn.ekingdom.mobile.icafeemployee.ui.dashboard

import android.os.Bundle
import android.view.View
import com.ekingdom.common.view.EKRecyclerVIew
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_home.*
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


    companion object{
        fun newInstance(): DashBoardFragment {
            val args = Bundle()
            val fragment = DashBoardFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun init() {
        (activity as? MainActivity)?.setToolbarTitle(getString(R.string.dashboard))
    }

    override fun initView(view: View) {
        initRCVWorkStations(view)
//        initRCVServices(view)
    }

    override fun getData() {

    }

    private fun initRCVWorkStations(view: View) {
        listWorkStationsAdapter = ListWorkStationsAdapter(getWSList()).apply {
            clickAction = { wsModel, position ->
            }
        }
        ekWSStatus.maxPage=100
        ekWSStatus.setUpAdapter(listWorkStationsAdapter)

        ekWSStatus.initLoadMore(object :EKRecyclerVIew.LoadMoreListener{
            override fun onLoadMore() {
                ekWSStatus.handler.postDelayed({
                    ekWSStatus.handleDataResponse(100)
                    listWorkStationsAdapter.addItems(getWSList())
                    listWorkStationsAdapter.notifyDataSetChanged()
                },5000)
            }

        } )

//        view.ekWSStatus.getRv().apply {
//            layoutParams.apply { height = ViewGroup.LayoutParams.WRAP_CONTENT }
//            isNestedScrollingEnabled = false
//            layoutManager = GridLayoutManager(context, 2)
//        }
//        rvData=EKRecyclerVIew<>
//        rvData.setAdapter(listWorkStationsAdapter)
    }

    private fun initRCVServices(view: View) {
        listWaitingServiceAdapter = ListWaitingServiceAdapter(getServicesList())
        ekWaitingOrder.setUpAdapter(listWaitingServiceAdapter)
    }

    private fun getWSList(): MutableList<WorkStationModel?> {
        return arrayListOf(
            WorkStationModel(workStationName = "36 máy", status = "A"),
            WorkStationModel(workStationName = "50 máy", status = "U"),
            WorkStationModel(workStationName = "4 máy", status = "M"),
            WorkStationModel(workStationName = "10 máy", status = "E")
        )
    }

    private fun getServicesList(): MutableList<Order?> {
        return arrayListOf(
            Order(orderId = "1", workStationId = 1, orderDate = Calendar.getInstance().time),
            Order(orderId = "2", workStationId = 2, orderDate = Calendar.getInstance().time),
            Order(orderId = "3", workStationId = 3, orderDate = Calendar.getInstance().time),
            Order(orderId = "4", workStationId = 4, orderDate = Calendar.getInstance().time),
            Order(orderId = "5", workStationId = 5, orderDate = Calendar.getInstance().time)
        )
    }
}
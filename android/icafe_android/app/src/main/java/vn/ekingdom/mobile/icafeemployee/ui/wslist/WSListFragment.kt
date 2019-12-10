package vn.ekingdom.mobile.icafeemployee.ui.wslist

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_ws_list.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.ekingdom.mobile.icafeemployee.MainActivity
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.model.WorkStationModel
import vn.ekingdom.mobile.icafeemployee.ui.common.BaseFragment
import vn.ekingdom.mobile.icafeemployee.ui.dashboard.adapter.ListWorkStationsAdapter

class WSListFragment : BaseFragment<WSListViewModel>() {
    override val layoutResID: Int
        get() = R.layout.fragment_ws_list
    override val viewModel by viewModel<WSListViewModel>()
    private lateinit var listWorkStationsAdapter: ListWorkStationsAdapter

    override fun init() {
//        (activity as? MainActivity)?.setToolbarTitle()
    }

    override fun initView(view: View) {
        initRCVWorkStations(view)
    }

    private fun initRCVWorkStations(view: View) {
        listWorkStationsAdapter = ListWorkStationsAdapter(R.layout.rcv_item_ws_status, getWSList())
        view.ekWSList.apply {
            removeDivider()
        }
        view.ekWSList.getRcvData().apply {
            isNestedScrollingEnabled = false
            layoutManager = GridLayoutManager(context, 2)
            adapter = listWorkStationsAdapter
        }
    }

    private fun getWSList(): MutableList<WorkStationModel> {
        return arrayListOf(
            WorkStationModel(workStationName = "Máy 01", status = "A"),
            WorkStationModel(workStationName = "Máy 02", status = "U"),
            WorkStationModel(workStationName = "Máy 03", status = "M"),
            WorkStationModel(workStationName = "Máy 04", status = "E")
        )
    }

    override fun getData() {
    }
}
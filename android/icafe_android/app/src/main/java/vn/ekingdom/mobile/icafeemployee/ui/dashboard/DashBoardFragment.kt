package vn.ekingdom.mobile.icafeemployee.ui.dashboard

import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.ui.common.BaseFragment

class DashBoardFragment : BaseFragment<DashBoardViewModel>() {
    override val viewModel by viewModel<DashBoardViewModel>()
    override val layoutResID: Int
        get() = R.layout.fragment_dashboard

    override fun init() {

    }

    override fun initView(view: View) {

    }

    override fun getData() {

    }
}
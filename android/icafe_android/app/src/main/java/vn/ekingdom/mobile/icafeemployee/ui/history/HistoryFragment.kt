package vn.ekingdom.mobile.icafeemployee.ui.history

import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.ui.common.BaseFragment

class HistoryFragment: BaseFragment<HistoryViewModel>() {
    override val layoutResID: Int
        get() = R.layout.fragment_history
    override val viewModel by viewModel<HistoryViewModel>()

    override fun init() {
    }

    override fun initView(view: View) {
    }

    override fun getData() {
    }
}
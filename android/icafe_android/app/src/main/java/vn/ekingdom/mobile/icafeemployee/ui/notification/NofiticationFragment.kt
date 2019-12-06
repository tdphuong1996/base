package vn.ekingdom.mobile.icafeemployee.ui.notification

import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.ui.common.BaseFragment

class NofiticationFragment: BaseFragment<NotificationViewModel>() {
    override val layoutResID: Int
        get() = R.layout.fragment_notification
    override val viewModel by viewModel<NotificationViewModel>()

    override fun init() {
    }

    override fun initView(view: View) {
    }

    override fun getData() {
    }
}
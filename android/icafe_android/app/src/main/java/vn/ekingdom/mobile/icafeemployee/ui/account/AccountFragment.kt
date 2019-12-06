package vn.ekingdom.mobile.icafeemployee.ui.account

import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.ui.common.BaseFragment

class AccountFragment: BaseFragment<AccountViewModel>() {
    override val layoutResID: Int
        get() = R.layout.fragment_account
    override val viewModel by viewModel<AccountViewModel>()

    override fun init() {
    }

    override fun initView(view: View) {
    }

    override fun getData() {
    }
}
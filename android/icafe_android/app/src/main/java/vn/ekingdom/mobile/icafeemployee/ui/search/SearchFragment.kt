package vn.ekingdom.mobile.icafeemployee.ui.search

import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.ui.common.BaseFragment

class SearchFragment : BaseFragment<SearchViewModel>(){
    override val layoutResID: Int
        get() = R.layout.fragment_search
    override val viewModel by viewModel<SearchViewModel>()

    override fun init() {
    }

    override fun initView(view: View) {
    }

    override fun getData() {
    }
}
package vn.ekingdom.mobile.icafeemployee.ui.home

import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.ekingdom.mobile.icafeemployee.MainActivity
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.ui.common.BaseFragment


class HomeFragment: BaseFragment<HomeViewModel>() {
    override val layoutResID: Int
        get() = R.layout.fragment_home
    override val viewModel by viewModel<HomeViewModel>()

    override fun init() {
    }

    override fun initView(view: View) {
        (activity as? MainActivity)?.showToolbar(true)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fmNavChild) as NavHostFragment
        NavigationUI.setupWithNavController(
            view.bottomNav,
            navHostFragment.navController
        )

        view.bottomNav.getOrCreateBadge(R.id.dashboardScreen).apply {
            backgroundColor = resources.getColor(R.color.lightColorPrimary)

        }
    }

    override fun getData() {
    }
}
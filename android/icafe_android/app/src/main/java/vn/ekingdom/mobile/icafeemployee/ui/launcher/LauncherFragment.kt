package vn.ekingdom.mobile.icafeemployee.ui.launcher

import android.os.Handler
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_launcher.view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.ekingdom.mobile.icafeemployee.EmployeeApplication
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.base.SharedReferenceHelper
import vn.ekingdom.mobile.icafeemployee.ui.common.BaseFragment

class LauncherFragment: BaseFragment<LauncherViewModel>() {
    override val layoutResID: Int
        get() = R.layout.fragment_launcher
    override val viewModel by viewModel<LauncherViewModel>()

    override fun init() {
        EmployeeApplication.userModel = sharedReferenceHelper.getUserLogin()

    }

    override fun initView(view: View) {
        Handler().postDelayed({
            val extras = FragmentNavigatorExtras(
                view.imgIcon to "image_transition_0"
            )
            val destination = if (EmployeeApplication.userModel == null) {
                R.id.action_login
            } else {
                R.id.action_home
            }

            findNavController().navigate(
                destination,
                null,
                NavOptions.Builder().setPopUpTo(
                    R.id.launcherScreen,
                    true
                ).build(),
                extras)
        }, 500)

    }

    override fun getData() {
    }
}
package vn.ekingdom.mobile.icafeemployee.ui.account.forgotpass

import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_forgot_pass.*
import kotlinx.android.synthetic.main.fragment_forgot_pass.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.ui.common.BaseFragment

class ForgotPassFragment: BaseFragment<ForgotPassViewModel>() {
    override val layoutResID: Int
        get() = R.layout.fragment_forgot_pass
    override val viewModel by viewModel<ForgotPassViewModel>()

    override fun init() {
    }

    override fun initView(view: View) {
    }

    override fun bindEvent(view: View) {
        super.bindEvent(view)
        view.tvBackToLogin.setOnClickListener { gotoLoginScreen() }
        view.btnSendRequest.setOnClickListener {
            onClickSendRequest(it)
        }
    }

    private fun onClickSendRequest(view: View) {
        if (groupForgotPass.visibility == View.VISIBLE) {
            sendRequestSuccess()
        } else {
            gotoLoginScreen()
        }
    }

    private fun sendRequestSuccess() {
        groupForgotPass?.visibility = View.GONE
        view?.btnSendRequest?.text = getString(R.string.OK)
        view?.tvForgetPassMessage?.text = getString(R.string.forget_pass_success_message)
    }

    private fun gotoLoginScreen() {
        findNavController().popBackStack(R.id.loginScreen, false)
    }

    override fun getData() {

    }
}
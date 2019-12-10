package vn.ekingdom.mobile.icafeemployee.ui.account.forgotpass

import android.annotation.SuppressLint
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ekingdom.common.extension.*
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_forgot_pass.*
import kotlinx.android.synthetic.main.fragment_forgot_pass.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.ui.common.BaseFragment

@SuppressLint("CheckResult")
class ForgotPassFragment: BaseFragment<ForgotPassViewModel>() {
    override val layoutResID: Int
        get() = R.layout.fragment_forgot_pass
    override val viewModel by viewModel<ForgotPassViewModel>()
    private lateinit var userNameChange: Observable<CharSequence>
    private lateinit var emailChange: Observable<CharSequence>

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

        userNameChange = view.edtUserName.textChanges()
        emailChange = view.edtEmail.textChanges()
    }

    private fun onClickSendRequest(view: View) {
        if (groupForgotPass.visibility == View.VISIBLE) {
            if (validateData()) {
                sendRequestSuccess()
            }
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

    /**
     * Kiểm tra 2 field tài khoản, email
     */
    private fun validateData() : Boolean {
        if (view?.edtUserName?.text?.validate(USERNAME_VALIDATOR) == false) {
            context?.getGenericDialog(R.string.notification, R.string.invalid_username)
                ?.apply { clickCallback = {
                    this@ForgotPassFragment.apply {
                        view?.edtUserName?.setBackgroundResource(R.drawable.corner_edittext_invalid_5dp)
                        view?.edtUserName?.requestFocus()
                        validateRuntimeUserName()
                    }
                }}
                ?.showIfNonExistent(childFragmentManager, getClassSimpleTag())
            return false
        } else if (view?.edtEmail?.text?.validate(EMAIL_VALIDATOR) == false) {
            context?.getGenericDialog(R.string.notification, R.string.invalid_email)
                ?.apply { clickCallback = {
                    this@ForgotPassFragment.apply {
                        view?.edtEmail?.setBackgroundResource(R.drawable.corner_edittext_invalid_5dp)
                        view?.edtEmail?.requestFocus()
                        validateRuntimeEmail()
                    }
                }}
                ?.showIfNonExistent(childFragmentManager, getClassSimpleTag())
            return false
        }
        return true
    }

    private fun validateRuntimeUserName() {
        userNameChange.subscribe {
            val isValid = it.validate(USERNAME_VALIDATOR)
            if (isValid) {
                view?.edtUserName?.setBackgroundResource(R.drawable.corner_edittext_5dp)
            } else {
                view?.edtUserName?.setBackgroundResource(R.drawable.corner_edittext_invalid_5dp)
            }
        }
    }

    private fun validateRuntimeEmail() {
        emailChange.subscribe {
            val isValid = it.validate(EMAIL_VALIDATOR)
            if (isValid) {
                view?.edtEmail?.setBackgroundResource(R.drawable.corner_edittext_5dp)
            } else {
                view?.edtEmail?.setBackgroundResource(R.drawable.corner_edittext_invalid_5dp)
            }
        }
    }

    override fun getData() {

    }
}
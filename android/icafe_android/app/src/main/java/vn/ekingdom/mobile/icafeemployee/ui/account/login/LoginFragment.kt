package vn.ekingdom.mobile.icafeemployee.ui.account.login

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ekingdom.common.extension.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.ui.common.BaseFragment

class LoginFragment : BaseFragment<LoginViewModel>(){
    override val layoutResID: Int
        get() = R.layout.fragment_login
    override val viewModel by activityViewModels<LoginViewModel>()

    override fun init() {
    }

    override fun initView(view: View) {
    }

    override fun bindEvent(view: View) {
        super.bindEvent(view)
        view.edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer {
            when(it) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    goToHomeScreen()
                }
                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> {

                }
                LoginViewModel.AuthenticationState.UNAUTHENTICATED -> {

                }
            }
        })

        view.btnLogin.setOnClickListener {
            goToHomeScreen()
        }

        view.tvForgetPass.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_forgot_pass)
        }

        view.imgEndPassword.setOnClickListener {
            if (view.edtPassword.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                view.edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                view.imgEndPassword.setImageResource(R.drawable.ic_icshowpass)
                view.edtPassword.setSelection(view.edtPassword.text?.length ?: 0)
            } else {
                view.edtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                view.imgEndPassword.setImageResource(R.drawable.ic_ichidepass)
                view.edtPassword.setSelection(view.edtPassword.text?.length ?: 0)
            }
        }
    }

    private fun goToHomeScreen() {
        if (validateData())
            findNavController().navigate(R.id.action_loginScreen_to_homeScreen)
    }

    private fun validateData() : Boolean {
        if (view?.edtUserName?.text?.validate(USERNAME_VALIDATOR) == false) {
            context?.getGenericDialog(R.string.notification, R.string.invalid_username)
                ?.apply { clickCallback = {
                    if (it) {

                    }
                }}
                ?.showIfNonExistent(childFragmentManager, getClassSimpleTag())
            return false
        } else if (view?.edtPassword?.text?.validate(PASSWORD_VALIDATOR) == false) {
            context?.getGenericDialog(R.string.notification, R.string.invalid_password)
                ?.apply { clickCallback = {

                }}
                ?.showIfNonExistent(childFragmentManager, getClassSimpleTag())
            return false
        }
        return true
    }

    override fun getData() {
    }
}
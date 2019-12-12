package vn.ekingdom.mobile.icafeemployee.ui.account.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import com.ekingdom.common.extension.*
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_login.view.*
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.model.UserModel
import vn.ekingdom.mobile.icafeemployee.ui.common.BaseFragment
import vn.ekingdom.mobile.icafeemployee.ui.dashboard.DashBoardFragment
import vn.ekingdom.mobile.icafeemployee.ui.home.HomeFragment

@SuppressLint("CheckResult")
class LoginFragment : BaseFragment<LoginViewModel>(){

    companion object{
        fun newInstance(): LoginFragment {
            val args = Bundle()
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override val layoutResID: Int
        get() = R.layout.fragment_login
    override val viewModel by activityViewModels<LoginViewModel>()



    private lateinit var userNameChange: Observable<CharSequence>
    private lateinit var passwordChange: Observable<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun init() {

    }

    override fun initView(view: View) {
    }


    @SuppressLint("CheckResult")
    override fun bindEvent(view: View) {
        super.bindEvent(view)
        view.edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
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
            it.hideKeyboard()
            goToHomeScreen()
        }

        view.tvForgetPass.setOnClickListener {
        }

        view.imgEndPassword.setOnClickListener {
            showHidePassWord(view.edtPassword?.transformationMethod == PasswordTransformationMethod.getInstance())
        }

        userNameChange = view.edtUserName.textChanges()
        passwordChange = view.edtPassword.textChanges()
    }

    private fun goToHomeScreen() {
        if (validateData()) {
            //Todo change temp data
            val userLogin = UserModel().apply {
                userId = 1
                userName = view?.edtUserName?.text.toString()
                password = view?.edtPassword?.text.toString()
            }
            sharedReferenceHelper.setUserLogin(userLogin)
            replaceFragment(DashBoardFragment.newInstance(),false)
        }
    }

    /**
     * Kiểm tra 2 field tài khoản, mật khẩu
     */
    private fun validateData() : Boolean {
        if (view?.edtUserName?.text?.validate(USERNAME_VALIDATOR) == false) {
            context?.getGenericDialog(R.string.notification, R.string.invalid_username)
                ?.apply { clickCallback = {
                    this@LoginFragment.apply {
                        view?.edtUserName?.setBackgroundResource(R.drawable.corner_edittext_invalid_5dp)
                        view?.edtUserName?.requestFocus()
                        validateRuntimeUserName()
                    }
                }}
                ?.showIfNonExistent(childFragmentManager, getClassSimpleTag())
            return false
        } else if (view?.edtPassword?.text?.validate(PASSWORD_VALIDATOR) == false) {
            context?.getGenericDialog(R.string.notification, R.string.invalid_password)
                ?.apply { clickCallback = {
                    this@LoginFragment.apply {
                        view?.edtPassword?.setBackgroundResource(R.drawable.corner_edittext_invalid_5dp)
                        view?.edtPassword?.requestFocus()
                        validateRuntimePassword()
                    }
                }}
                ?.showIfNonExistent(childFragmentManager, getClassSimpleTag())
            return false
        }
        return true
    }

    /**
     * Ẩn/hiện field mật khẩu
     */
    private fun showHidePassWord(isShow: Boolean) {
        if (!isShow) {
            view?.edtPassword?.transformationMethod = PasswordTransformationMethod.getInstance()
            view?.imgEndPassword?.setImageResource(R.drawable.ic_icshowpass)
            view?.edtPassword?.setSelection(view?.edtPassword?.text?.length ?: 0)
        } else {
            view?.edtPassword?.transformationMethod = HideReturnsTransformationMethod.getInstance()
            view?.imgEndPassword?.setImageResource(R.drawable.ic_ichidepass)
            view?.edtPassword?.setSelection(view?.edtPassword?.text?.length ?: 0)
        }
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

    private fun validateRuntimePassword() {
        passwordChange.subscribe {
            val isValid = it.validate(PASSWORD_VALIDATOR)
            if (isValid) {
                view?.edtPassword?.setBackgroundResource(R.drawable.corner_edittext_5dp)
            } else {
                view?.edtPassword?.setBackgroundResource(R.drawable.corner_edittext_invalid_5dp)
            }
        }
    }


    override fun getData() {
    }
}
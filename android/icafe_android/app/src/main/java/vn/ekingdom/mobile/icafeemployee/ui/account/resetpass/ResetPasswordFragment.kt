package vn.ekingdom.mobile.icafeemployee.ui.account.resetpass

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.ekingdom.common.extension.*
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_resetpass.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.ui.common.BaseFragment

class ResetPasswordFragment : BaseFragment<ResetPasswordViewModel>(){
    override val layoutResID: Int
        get() = R.layout.fragment_resetpass
    override val viewModel by viewModel<ResetPasswordViewModel>()
    private lateinit var passChange: Observable<CharSequence>
    private lateinit var newPassChange: Observable<CharSequence>
    private lateinit var confirmNewPassChange: Observable<CharSequence>

    override fun init() {
    }

    override fun initView(view: View) {
        view.imgCurrentPass.setOnClickListener {
            showHidePassWord(view.edtCurrentPass?.transformationMethod == PasswordTransformationMethod.getInstance(),
                view.edtCurrentPass,
                view.imgCurrentPass)
        }

        view.imgEndNewPassword.setOnClickListener {
            showHidePassWord(view.edtNewPassword?.transformationMethod == PasswordTransformationMethod.getInstance(),
                view.edtNewPassword,
                view.imgEndNewPassword)
        }

        view.imgEndConfirmNewPassword.setOnClickListener {
            showHidePassWord(view.edtConfirmNewPassword?.transformationMethod == PasswordTransformationMethod.getInstance(),
                view.edtConfirmNewPassword,
                view.imgEndConfirmNewPassword)
        }
        view.btnChangePass.setOnClickListener { validateData() }
    }

    /**
     * Ẩn/hiện field mật khẩu
     */
    private fun showHidePassWord(
        isShow: Boolean,
        editText: AppCompatEditText?,
        imageView: AppCompatImageView?) {

        if (!isShow) {
            editText?.transformationMethod = PasswordTransformationMethod.getInstance()
            imageView?.setImageResource(R.drawable.ic_icshowpass)
            editText?.setSelection(editText.text?.length ?: 0)
        } else {
            editText?.transformationMethod = HideReturnsTransformationMethod.getInstance()
            imageView?.setImageResource(R.drawable.ic_ichidepass)
            editText?.setSelection(editText.text?.length ?: 0)
        }

    }

    private fun validateData():Boolean {
        var messageResId: Int = -1
        val currentPass = view?.edtCurrentPass?.text?.toString()
        val newPass = view?.edtNewPassword?.text?.toString()
        val confirmNewPass = view?.edtConfirmNewPassword?.text?.toString()

        when {
            currentPass?.validate(PASSWORD_VALIDATOR) == false -> {
                messageResId = R.string.invalid_current_password
                changeEditTextBackground(false, view?.edtCurrentPass)
                view?.edtCurrentPass?.requestFocus()
            }
            newPass?.validate(PASSWORD_VALIDATOR) == false -> {
                messageResId = R.string.invalid_new_password
                changeEditTextBackground(false, view?.edtNewPassword)
                view?.edtNewPassword?.requestFocus()
            }
            currentPass == newPass -> {
                messageResId = R.string.invalid_current_and_new_pass
                changeEditTextBackground(false, view?.edtNewPassword)
                view?.edtNewPassword?.requestFocus()
            }
            newPass != confirmNewPass -> {
                messageResId = R.string.invalid_new_and_confirm_pass
                changeEditTextBackground(false, view?.edtConfirmNewPassword)
                view?.edtConfirmNewPassword?.requestFocus()
            }
        }

        if (messageResId != -1) {
            context?.getGenericDialog(R.string.notification, messageResId)
                .apply {

                }?.showIfNonExistent(childFragmentManager, getClassSimpleTag())
        }
        return messageResId == -1
    }

    /**
     * @param isValid = true: hợp lệ, ngược lại không hợp lệ
     */
    private fun changeEditTextBackground(isValid: Boolean, editText: AppCompatEditText?) {
        if (isValid) {
            editText?.setBackgroundResource(R.drawable.corner_edittext_5dp)
        } else {
            editText?.setBackgroundResource(R.drawable.corner_edittext_invalid_5dp)
        }
    }

    override fun getData() {
    }
}
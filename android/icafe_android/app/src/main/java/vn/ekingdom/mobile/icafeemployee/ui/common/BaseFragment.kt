package vn.ekingdom.mobile.icafeemployee.ui.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ekingdom.common.extension.getClassSimpleTag
import com.ekingdom.common.extension.showIfNonExistent
import com.ekingdom.common.extension.showLoading
import com.ekingdom.common.view.EKMessageDialogFragment
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.model.AsyncState
import vn.ekingdom.mobile.icafeemployee.utils.Theme
import org.koin.android.ext.android.inject
import vn.ekingdom.mobile.icafeemployee.base.BaseViewModel
import vn.ekingdom.mobile.icafeemployee.base.SharedReferenceHelper


abstract class BaseFragment<VM : BaseViewModel>() : Fragment(){
    protected abstract val layoutResID: Int
    protected abstract val viewModel: VM
    val sharedReferenceHelper: SharedReferenceHelper by inject()
    private var isInit = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // create ContextThemeWrapper from the original Activity Context with the custom theme
        val contextThemeWrapper: Context =
            ContextThemeWrapper(activity, getThemeID())

        // clone the inflater using the ContextThemeWrapper
        val localInflater = inflater.cloneInContext(contextThemeWrapper)
        init()
        return localInflater.inflate(layoutResID, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        if (!isInit) {
            initView(view)
            bindEvent(view)
            getData()
//        }
//        isInit = true
    }

    abstract fun init()
    abstract fun initView(view: View)

    @CallSuper
    open fun bindEvent(view: View) {
        viewModel.loadingState.observe(this, Observer { loadingState ->
            loadingState.let {
                when (it) {
                    is AsyncState.Started -> showLoading(true)
                    is AsyncState.Completed -> showLoading(false)
                    is AsyncState.Failed -> {
                        //handle error here
                        EKMessageDialogFragment.newInstance(R.string.app_name, R.string.app_name)
                            .apply { clickCallback = {
                                //Click callback handle here
                                //true: click ok; false: click cancel
                            }}
                            .showIfNonExistent(
                            requireParentFragment().childFragmentManager,
                            getClassSimpleTag()
                        )
                    }
                }
            }
        })
    }

    open fun getThemeID(): Int {
//        var theme = sharedReferenceHelper.getTheme()
//        return if (theme.equals(Theme.LIGHT.name, true)) {
//            R.style.LightTheme
//        } else {
//            R.style.MintTheme
//        }
        return R.style.LightTheme
    }

    abstract fun getData()

    fun showLoading(isShow: Boolean) {
        val rootView = view as? ViewGroup
        rootView?.showLoading(isShow)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    fun addFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).addFragment(fragment, isAddToBackStack)
        }
    }

    fun replaceFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).replaceFragment(fragment, isAddToBackStack)
        }
    }

}
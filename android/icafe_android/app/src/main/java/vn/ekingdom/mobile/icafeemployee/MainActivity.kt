package vn.ekingdom.mobile.icafeemployee

import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ekingdom.common.base.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import vn.ekingdom.mobile.icafeemployee.ui.account.login.LoginFragment
import vn.ekingdom.mobile.icafeemployee.ui.common.BaseActivity
import vn.ekingdom.mobile.icafeemployee.ui.dashboard.DashBoardFragment


class MainActivity(override val layoutRes: Int=R.layout.activity_main) : BaseActivity() {

    override fun init() {
    }

    override fun initView() {
    }

    override fun bindEvent() {
    }

    override fun getData() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.sharedElementEnterTransition.duration = 2000
        window.sharedElementReturnTransition.setDuration(2000).interpolator =
            DecelerateInterpolator()
        rootLayout.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or
                SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        replaceFragment(DashBoardFragment.newInstance(),false)
    }

    fun getToolbar() = searchViewToolbar

    fun showToolbar(isShow: Boolean){
        searchViewToolbar.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    fun setToolbarTitle(title: String) {
        searchViewToolbar.setTitle(title)
    }

}

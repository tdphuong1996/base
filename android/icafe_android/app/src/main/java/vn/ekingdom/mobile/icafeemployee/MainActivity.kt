package vn.ekingdom.mobile.icafeemployee

import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.sharedElementEnterTransition.duration = 2000
        window.sharedElementReturnTransition.setDuration(2000).interpolator =
            DecelerateInterpolator()
        rootLayout.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or
                SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        navController = findNavController(R.id.fmContainer)
        searchViewToolbar.iSearchShow = {
            if (it) {
                navController.navigate(R.id.action_home_to_search)
            } else {
                navController.popBackStack()
            }
        }
    }

    fun getToolbar() = searchViewToolbar

    fun showToolbar(isShow: Boolean){
        searchViewToolbar.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    fun setToolbarTitle(title: String) {
        searchViewToolbar.setTitle(title)
    }

    override fun onSupportNavigateUp() : Boolean {
        if (searchViewToolbar.isSearchShowing()){
            searchViewToolbar.closeSearch()
            return false
        }
        return navController.navigateUp()
    }

    override fun onBackPressed() {
        if (searchViewToolbar.isSearchShowing()){
            searchViewToolbar.closeSearch()
        } else {
            super.onBackPressed()
        }
    }

}

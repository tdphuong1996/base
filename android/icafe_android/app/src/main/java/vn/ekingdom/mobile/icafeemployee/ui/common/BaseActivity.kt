package vn.ekingdom.mobile.icafeemployee.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import vn.ekingdom.mobile.icafeemployee.R
import vn.ekingdom.mobile.icafeemployee.base.SharedReferenceHelper
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layoutRes: Int

    private val sharedReferenceHelper: SharedReferenceHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutRes)
        init()
        initView()
        bindEvent()
        getData()
    }

    /**
     * Khởi tạo các biến cần thiết cho activity
     */
    abstract fun init()

    /**
     * Khởi tạo view
     */
    abstract fun initView()

    /**
     * Set event listener
     */
    abstract fun bindEvent()

    /**
     * Lấy dữ liệu
     */
    abstract fun getData()


    fun addFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        addReplaceFragment(fragment, false, isAddToBackStack, true)
    }

    fun replaceFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        addReplaceFragment(fragment, true, isAddToBackStack, true)
    }

    private fun addReplaceFragment(
        fragment: Fragment?,
        isReplace: Boolean,
        isAddToBackStack: Boolean,
        isAnim: Boolean
    ) {
        val fragmentManager = supportFragmentManager
        if (fragmentManager != null && fragment != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            if (isAnim)
                fragmentTransaction.setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
            if (isReplace)
                fragmentTransaction.replace(R.id.fmContainer, fragment)
            else {
                val currentFragment = supportFragmentManager.findFragmentById(R.id.fmContainer)
                if (currentFragment != null) {
                    fragmentTransaction.hide(currentFragment)
                }
                fragmentTransaction.add(R.id.fmContainer, fragment, fragment.javaClass.simpleName)
            }
            if (isAddToBackStack) {
                fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
            }
            fragmentTransaction.commit()
        }
    }


}
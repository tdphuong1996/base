package vn.ekingdom.mobile.icafeemployee.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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


}
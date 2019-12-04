package vn.ekingdom.mobile.icafeemployee.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vn.ekingdom.mobile.icafeemployee.model.AsyncState
import org.koin.core.context.unloadKoinModules

abstract class BaseViewModel: ViewModel() {

    val loadingState: MutableLiveData<AsyncState>
            by lazy { MutableLiveData<AsyncState>() }


    fun showLoading(isShow: Boolean) {
        loadingState.value = if (isShow) AsyncState.Started else AsyncState.Completed
    }

}
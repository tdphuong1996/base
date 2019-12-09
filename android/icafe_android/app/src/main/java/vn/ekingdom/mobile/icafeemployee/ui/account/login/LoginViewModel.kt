package vn.ekingdom.mobile.icafeemployee.ui.account.login

import androidx.lifecycle.MutableLiveData
import vn.ekingdom.mobile.icafeemployee.base.BaseViewModel

class LoginViewModel: BaseViewModel() {
    enum class AuthenticationState {
        UNAUTHENTICATED,        // Initial state, the user needs to authenticate
        AUTHENTICATED  ,        // The user has authenticated successfully
        INVALID_AUTHENTICATION  // Authentication failed
    }

    val authenticationState = MutableLiveData<AuthenticationState>()
    var username: String

    init {
        // In this example, the user is always unauthenticated when MainActivity is launched
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
        username = ""
    }
}
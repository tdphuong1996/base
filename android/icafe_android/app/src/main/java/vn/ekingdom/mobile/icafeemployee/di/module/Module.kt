package vn.ekingdom.mobile.icafeemployee.di.module

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import vn.ekingdom.mobile.icafeemployee.base.SharedReferenceHelper
import vn.ekingdom.mobile.icafeemployee.ui.account.AccountViewModel
import vn.ekingdom.mobile.icafeemployee.ui.account.forgotpass.ForgotPassViewModel
import vn.ekingdom.mobile.icafeemployee.ui.dashboard.DashBoardViewModel
import vn.ekingdom.mobile.icafeemployee.ui.history.HistoryViewModel
import vn.ekingdom.mobile.icafeemployee.ui.home.HomeViewModel
import vn.ekingdom.mobile.icafeemployee.ui.launcher.LauncherViewModel
import vn.ekingdom.mobile.icafeemployee.ui.account.login.LoginViewModel
import vn.ekingdom.mobile.icafeemployee.ui.notification.NotificationViewModel
import vn.ekingdom.mobile.icafeemployee.ui.search.SearchViewModel

val appData = module {
    single { SharedReferenceHelper(androidContext()) }
}

val viewModelModule = module {
    viewModel {
        HomeViewModel()
    }
    viewModel {
        SearchViewModel()
    }
    viewModel {
        DashBoardViewModel()
    }
    viewModel {
        HistoryViewModel()
    }
    viewModel {
        NotificationViewModel()
    }
    viewModel {
        AccountViewModel()
    }
    viewModel {
        LoginViewModel()
    }
    viewModel {
        LauncherViewModel()
    }
    viewModel {
        ForgotPassViewModel()
    }

}

val repositoryModule = module {
//    factory {
//        PostRepository(get())
//    }
}


val appModule = remoteModule + listOf(
    appData,
    viewModelModule,
    repositoryModule
)

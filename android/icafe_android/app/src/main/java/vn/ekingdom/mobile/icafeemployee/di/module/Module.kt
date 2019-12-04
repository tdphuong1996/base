package vn.ekingdom.mobile.icafeemployee.di.module

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import vn.ekingdom.mobile.icafeemployee.base.SharedReferenceHelper

val appData = module {
    single { SharedReferenceHelper(androidContext()) }
}

val viewModelModule = module {
//    viewModel {
//        DemoViewModel(get())
//    }
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

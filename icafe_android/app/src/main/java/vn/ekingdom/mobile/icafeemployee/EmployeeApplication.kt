package vn.ekingdom.mobile.icafeemployee

import android.app.Application
import androidx.multidex.MultiDex
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import vn.ekingdom.mobile.icafeemployee.di.module.appModule

class EmployeeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@EmployeeApplication)
            modules(appModule)
        }
    }

}
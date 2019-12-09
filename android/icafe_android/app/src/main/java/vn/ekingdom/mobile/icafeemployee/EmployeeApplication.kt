package vn.ekingdom.mobile.icafeemployee

import android.app.Application
import androidx.multidex.MultiDex
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import vn.ekingdom.mobile.icafeemployee.di.module.appModule
import vn.ekingdom.mobile.icafeemployee.model.UserModel

class EmployeeApplication : Application() {

    companion object{
        var userModel: UserModel? = null
    }

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
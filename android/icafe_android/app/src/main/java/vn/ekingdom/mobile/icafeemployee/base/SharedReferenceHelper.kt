package vn.ekingdom.mobile.icafeemployee.base

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import vn.ekingdom.mobile.icafeemployee.EmployeeApplication
import vn.ekingdom.mobile.icafeemployee.model.UserModel
import vn.ekingdom.mobile.icafeemployee.utils.Theme


class SharedReferenceHelper (appContext: Context) {

    companion object {
        const val THEME = "Theme"
        const val USER = "USER"
    }

    private var sharedPreferences: SharedPreferences =
        appContext.getSharedPreferences("APP_DATA", Context.MODE_PRIVATE)

    fun helloWord() = sharedPreferences.getString("hell", "Hell")

    fun getTheme() = sharedPreferences.getString(THEME, Theme.LIGHT.name) ?: Theme.LIGHT.name

    fun setTheme(theme: Theme){
        sharedPreferences.edit().putString(THEME, theme.name).apply()
    }

    fun setUserLogin(userModel: UserModel) {
        EmployeeApplication.userModel = userModel
        sharedPreferences.edit().putString(USER, Gson().toJson(userModel)).apply()
    }

    fun getUserLogin(): UserModel? {
        val string = sharedPreferences.getString(USER, "")
        if (string?.isEmpty() == true) return null
        return Gson().fromJson(string, UserModel::class.java)
    }
}
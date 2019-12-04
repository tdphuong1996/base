package vn.ekingdom.mobile.icafeemployee.base

import android.content.Context
import android.content.SharedPreferences
import vn.ekingdom.mobile.icafeemployee.utils.Theme


class SharedReferenceHelper (appContext: Context) {

    companion object {
        const val THEME = "Theme"
    }

    private var sharedPreferences: SharedPreferences =
        appContext.getSharedPreferences("APP_DATA", Context.MODE_PRIVATE)

    fun helloWord() = sharedPreferences.getString("hell", "Hell")

    fun getTheme() = sharedPreferences.getString(THEME, Theme.LIGHT.name) ?: Theme.LIGHT.name

    fun setTheme(theme: Theme){
        sharedPreferences.edit().putString(THEME, theme.name).apply()
    }
}
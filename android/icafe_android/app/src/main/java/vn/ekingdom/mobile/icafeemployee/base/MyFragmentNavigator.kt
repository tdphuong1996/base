package vn.ekingdom.mobile.icafeemployee.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator

@Navigator.Name("custom_fragment")
class MyFragmentNavigator(
    private val context: Context,
    private val fm: FragmentManager,
    private val containerId: Int
) : FragmentNavigator(context, fm, containerId) {


    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        val tag = destination.id.toString()
        val transaction = fm.beginTransaction()

        var initialNavigate = false
        val currentFragment = fm.primaryNavigationFragment
        if (currentFragment != null) {
            transaction.detach(currentFragment)
        } else {
            initialNavigate = true
        }

        var fragment = fm.findFragmentByTag(tag)
        if (fragment == null) {
            val className = destination.className
            fragment = fm.fragmentFactory.instantiate(context.classLoader, className)
            transaction.add(containerId, fragment, tag)
        } else {
            transaction.attach(fragment)
        }

        transaction.setPrimaryNavigationFragment(fragment)
        transaction.setReorderingAllowed(true)
        transaction.commitNow()

        return if (initialNavigate) {
            destination
        } else {
            null
        }
    }
}
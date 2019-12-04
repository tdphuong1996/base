package vn.ekingdom.mobile.icafeemployee.base

import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.navigation.fragment.NavHostFragment

class MyNavHostFragment : NavHostFragment() {


    override fun createFragmentNavigator() = MyFragmentNavigator(requireContext(), childFragmentManager, id)

    companion object {
        private val KEY_GRAPH_ID = "android-support-nav:fragment:graphId"
        private val KEY_START_DESTINATION_ARGS =
            "android-support-nav:fragment:startDestinationArgs"
        private val KEY_NAV_CONTROLLER_STATE =
            "android-support-nav:fragment:navControllerState"
        private val KEY_DEFAULT_NAV_HOST = "android-support-nav:fragment:defaultHost"

        fun create(@NavigationRes graphResId: Int): MyNavHostFragment {
            return create(graphResId, null)
        }

        fun create(
            @NavigationRes graphResId: Int,
            startDestinationArgs: Bundle?
        ): MyNavHostFragment {
            var b: Bundle? = null
            if (graphResId != 0) {
                b = Bundle()
                b.putInt(KEY_GRAPH_ID, graphResId)
            }
            if (startDestinationArgs != null) {
                if (b == null) {
                    b = Bundle()
                }
                b.putBundle(KEY_START_DESTINATION_ARGS, startDestinationArgs)
            }
            val result = MyNavHostFragment()
            if (b != null) {
                result.arguments = b
            }
            return result
        }
    }
}
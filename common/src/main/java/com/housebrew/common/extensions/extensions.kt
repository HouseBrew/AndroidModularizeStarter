package com.housebrew.common.extensions

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import com.housebrew.common.DEEPLINK_PREFIX

/**
 * Extension for checking network status
 */
fun Context.checkNetworkStatus(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}

/**
 * Extension for converting dp to px
 */
fun Resources.dpToPx(dp: Float) = dp * Resources.getSystem().displayMetrics.density

/**
 * Extension for converting px to dp
 */
fun Resources.pxToDp(px: Float) = px / (Resources.getSystem().displayMetrics.densityDpi / 160f)

/**
 * Extension for checking non case sensitive equal for String
 */
fun String.ignoreCaseEqual(s: String) = this.toLowerCase() == s.toLowerCase()

/**
 * Extension for appending deep link prefix to a String
 */
fun String.toAppDeepLink() = "$DEEPLINK_PREFIX$this"
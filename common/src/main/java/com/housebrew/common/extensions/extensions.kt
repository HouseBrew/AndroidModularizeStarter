package com.housebrew.common.extensions

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager

fun Context.checkNetworkStatus(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}

fun Resources.dpToPx(dp: Float) = dp * Resources.getSystem().displayMetrics.density

fun Resources.pxToDp(px: Float) = px / (Resources.getSystem().displayMetrics.densityDpi / 160f)

fun String.ignoreCaseEqual(s: String) = this.toLowerCase() == s.toLowerCase()
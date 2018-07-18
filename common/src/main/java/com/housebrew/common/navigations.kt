package com.housebrew.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.airbnb.deeplinkdispatch.DeepLinkSpec

private const val DEEPLINK_PREFIX = "app://msapp"

object PageDeeplinks {
    const val LOGIN = "/login"
    const val MAIN = "/main"
}

object Navigator {
    fun toMainPage(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(PageDeeplinks.MAIN.toAppDeepLink())
        context.startActivity(intent)
    }

    fun toLoginPage(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(PageDeeplinks.LOGIN.toAppDeepLink())
        context.startActivity(intent)
    }
}

fun String.toAppDeepLink() = "$DEEPLINK_PREFIX$this"

@DeepLinkSpec(prefix = [DEEPLINK_PREFIX])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class AppDeepLink(vararg val value: String)

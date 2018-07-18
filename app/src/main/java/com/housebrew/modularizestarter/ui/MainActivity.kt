package com.housebrew.modularizestarter.ui

import android.os.Bundle
import com.airbnb.deeplinkdispatch.DeepLink
import com.housebrew.common.AppDeepLink
import com.housebrew.common.Navigator
import com.housebrew.common.PageDeeplinks
import com.housebrew.common.bases.BaseActivity
import com.housebrew.modularizestarter.R
import timber.log.Timber

@AppDeepLink(PageDeeplinks.MAIN)
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            Timber.d("Not launching MainActivity with DeepLink, proceed to Login")
            Navigator.toLoginPage(this)
            finish()
        }
        Timber.d("Launching MainActivity with DeepLink")
        setContentView(R.layout.activity_main)
    }
}

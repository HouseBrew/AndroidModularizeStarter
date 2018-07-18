package com.housebrew.modularizestarter

import com.facebook.stetho.Stetho
import com.housebrew.common.bases.BaseContext
import timber.log.Timber

class AppContext : BaseContext() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this@AppContext)
        }
    }
}
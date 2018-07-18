package com.housebrew.account.ui

import android.os.Bundle
import com.housebrew.account.R
import com.housebrew.common.AppDeepLink
import com.housebrew.common.Navigator
import com.housebrew.common.PageDeeplinks
import com.housebrew.common.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber

@AppDeepLink(PageDeeplinks.LOGIN)
class LoginActivity : BaseActivity() {
    // TODO dummy user system will be done in next milestone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtDummy.setOnClickListener {
            Timber.d("Nav from Login to Main")
            Navigator.toMainPage(this)
            finish()
        }
    }
}
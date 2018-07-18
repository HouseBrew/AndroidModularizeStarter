package com.housebrew.accountapp

import android.os.Bundle
import com.housebrew.common.Navigator
import com.housebrew.common.bases.BaseActivity

class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Navigator.toLoginPage(this)
        finish()
    }
}
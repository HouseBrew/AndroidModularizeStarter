package com.housebrew.modularizestarter

import android.os.Bundle
import com.housebrew.common.bases.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

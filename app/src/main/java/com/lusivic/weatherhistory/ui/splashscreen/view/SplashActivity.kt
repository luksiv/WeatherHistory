package com.lusivic.weatherhistory.ui.splashscreen.view

import android.content.Intent
import android.os.Bundle
import com.lusivic.weatherhistory.R
import com.lusivic.weatherhistory.ui.base.view.BaseActivity
import com.lusivic.weatherhistory.ui.main.view.MainActivity

class SplashActivity : BaseActivity(), SplashMVPView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        openMainActivity()
    }
    override fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
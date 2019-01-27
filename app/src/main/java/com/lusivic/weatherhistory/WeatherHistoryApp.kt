package com.lusivic.weatherhistory

import android.app.Application
import com.lusivic.weatherhistory.di.AppComponent
import com.lusivic.weatherhistory.di.AppModule
import com.lusivic.weatherhistory.di.DaggerAppComponent

class WeatherHistoryApp : Application() {

    lateinit var appComponent: AppComponent

    private fun initDagger(app: WeatherHistoryApp): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }
}
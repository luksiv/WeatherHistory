package com.lusivic.weatherhistory.ui.main.view

import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.ui.base.view.MVPView

interface IMainActivity : MVPView {
    fun setUp()
    fun showCurrentWeather(weather: WeatherReport)
    fun openHistoryActivity()
    fun showInsertSuccessMessage()
    fun showInsertFailedMessage()
    fun showPermissionDenied()
    fun isLocationPermissionGranted(): Boolean
    fun requestLocationPermission()
    fun getCurrentLocation()
}
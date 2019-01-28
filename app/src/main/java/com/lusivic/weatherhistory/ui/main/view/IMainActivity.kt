package com.lusivic.weatherhistory.ui.main.view

import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.ui.base.view.MVPView

interface MainMVPView : MVPView {
    fun showCurrentWeather(weather: WeatherReport)
    fun openHistoryActivity()

}
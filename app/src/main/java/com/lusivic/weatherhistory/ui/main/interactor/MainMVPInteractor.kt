package com.lusivic.weatherhistory.ui.main.interactor

import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.ui.base.interactor.MVPInteractor

interface MainMVPInteractor : MVPInteractor {

    fun getCurrentWeatherReport(): WeatherReport
}
package com.lusivic.weatherhistory.ui.main.interactor

import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.ui.base.interactor.MVPInteractor
import io.reactivex.Single

interface MainMVPInteractor : MVPInteractor {

    fun getCurrentWeatherReport(): Single<WeatherReport>
    fun insertWeatherReport(weatherReport: WeatherReport)
    fun getWeatherReportHistory(): Single<List<WeatherReport>>
}
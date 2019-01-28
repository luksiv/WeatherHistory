package com.lusivic.weatherhistory.ui.history.interactor

import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.ui.base.interactor.MVPInteractor
import io.reactivex.Single

interface IHistoryInteractor : MVPInteractor {
    fun getWeatherReportHistory(): Single<List<WeatherReport>>
}
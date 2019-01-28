package com.lusivic.weatherhistory.ui.history.interactor

import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReportRepo
import com.lusivic.weatherhistory.ui.base.interactor.BaseInteractor
import io.reactivex.Single
import javax.inject.Inject

class HistoryInteractor @Inject internal constructor(private val weatherReportRepo: WeatherReportRepo):
    BaseInteractor(),
    IHistoryInteractor {

    override fun getWeatherReportHistory(): Single<List<WeatherReport>> = weatherReportRepo.getAllWeatherReports()
}
package com.lusivic.weatherhistory.data.db.weatherReport

import io.reactivex.Single
import javax.inject.Inject

class WeatherReportRepo @Inject internal constructor(private val weatherReportDao: WeatherReportDao) :
    IWeatherReportRepo {
    override fun isWeatherHistoryRepoEmpty(): Single<Boolean> =
        Single.fromCallable { weatherReportDao.selectAll().isEmpty() }

    override fun insertWeatherReport(weatherReport: WeatherReport): Single<Boolean> {
        weatherReportDao.insert(weatherReport)
        return Single.just(true)
    }

    override fun getAllWeatherReports(): Single<List<WeatherReport>> =
        Single.fromCallable { weatherReportDao.selectAll() }

    override fun deleteWeatherReport(weatherReport: WeatherReport): Single<Boolean> {
        weatherReportDao.delete(weatherReport)
        return Single.just(true)
    }
}
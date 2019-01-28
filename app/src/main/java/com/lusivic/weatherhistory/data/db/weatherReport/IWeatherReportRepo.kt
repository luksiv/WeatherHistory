package com.lusivic.weatherhistory.data.db.weatherReport

import io.reactivex.Completable
import io.reactivex.Single

interface IWeatherReportRepo {

    fun isWeatherHistoryRepoEmpty(): Single<Boolean>
    fun insertWeatherReport(weatherReport: WeatherReport): Completable
    fun deleteWeatherReport(weatherReport: WeatherReport): Single<Boolean>
    fun getAllWeatherReports(): Single<List<WeatherReport>>

}
package com.lusivic.weatherhistory.data.db.weatherReport

import io.reactivex.Single

interface IWeatherReportRepo {

    fun isWeatherHistoryRepoEmpty(): Single<Boolean>
    fun insertWeatherReport(weatherReport: WeatherReport): Single<Boolean>
    fun deleteWeatherReport(weatherReport: WeatherReport): Single<Boolean>
    fun getAllWeatherReports(): Single<List<WeatherReport>>

}
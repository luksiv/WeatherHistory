package com.lusivic.weatherhistory.ui.main.interactor

import com.lusivic.weatherhistory.BuildConfig
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReportRepo
import com.lusivic.weatherhistory.data.network.OpenWeatherApiService
import com.lusivic.weatherhistory.data.network.OpenWeatherResponse
import com.lusivic.weatherhistory.ui.base.interactor.BaseInteractor
import io.reactivex.Single
import javax.inject.Inject

class MainInteractor @Inject internal constructor(
    private val weatherReportRepo: WeatherReportRepo,
    private val openWeatherApiService: OpenWeatherApiService
) : BaseInteractor(),
    IMainInteractor {

    override fun getCurrentWeatherReport(latitude: Double, longitude: Double): Single<OpenWeatherResponse> =
        openWeatherApiService.getCurrentWeatherByCoord(latitude, longitude, BuildConfig.OPEN_WEATHER_API_KEY)

    override fun insertWeatherReport(weatherReport: WeatherReport) =
        weatherReportRepo.insertWeatherReport(weatherReport)

}
package com.lusivic.weatherhistory.ui.main.interactor

import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.data.network.OpenWeatherResponse
import com.lusivic.weatherhistory.ui.base.interactor.MVPInteractor
import io.reactivex.Completable
import io.reactivex.Single

interface IMainInteractor : MVPInteractor {

    fun getCurrentWeatherReport(latitude: Float, longitude: Float): Single<OpenWeatherResponse>
    fun insertWeatherReport(weatherReport: WeatherReport): Completable
}
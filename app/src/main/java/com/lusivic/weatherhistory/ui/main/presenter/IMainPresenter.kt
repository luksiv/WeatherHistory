package com.lusivic.weatherhistory.ui.main.presenter

import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.ui.base.presenter.MVPPresenter
import com.lusivic.weatherhistory.ui.main.interactor.IMainInteractor
import com.lusivic.weatherhistory.ui.main.view.IMainActivity

interface IMainPresenter<V : IMainActivity, I : IMainInteractor> : MVPPresenter<V, I> {
    fun onLocationPermissionGranted(): Unit?
    fun onLocationPermissionDenied(): Unit?
    fun onViewReady(): Unit?
    fun onHistoryClick()
    fun onRefreshClick(): Unit?
    fun onSubmitClick(weatherReport: WeatherReport)
    fun getCurrentWeather(latitude: Double, longitude: Double)
}
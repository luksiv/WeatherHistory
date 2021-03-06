package com.lusivic.weatherhistory.ui.main.presenter

import android.util.Log
import com.lusivic.weatherhistory.data.db.weatherReport.LocationInfo
import com.lusivic.weatherhistory.data.db.weatherReport.Measurements
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherInfo
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.data.network.OpenWeatherResponse
import com.lusivic.weatherhistory.ui.base.presenter.BasePresenter
import com.lusivic.weatherhistory.ui.main.interactor.IMainInteractor
import com.lusivic.weatherhistory.ui.main.view.IMainActivity
import com.lusivic.weatherhistory.ui.main.view.MainActivity
import com.lusivic.weatherhistory.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter<V : IMainActivity, I : IMainInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) :
    BasePresenter<V, I>(
        interactor = interactor,
        schedulerProvider = schedulerProvider,
        compositeDisposable = disposable
    ), IMainPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        getView()?.let {
            it.showProgress()
            if (it.isLocationPermissionGranted()) it.setUp()
            else it.requestLocationPermission()
        }
    }

    override fun onLocationPermissionGranted() = getView()?.setUp()

    override fun onLocationPermissionDenied() = getView()?.let {
        it.hideProgress()
        it.showPermissionDenied()
    }


    override fun onViewReady() = getView()?.getCurrentLocation()

    override fun onHistoryClick() = (getView() as MainActivity).openHistoryActivity()

    override fun onRefreshClick() = getView()?.getCurrentLocation()

    override fun onSubmitClick(weatherReport: WeatherReport) {
        getView()?.showProgress()
        interactor?.let {
            compositeDisposable.add(
                it.insertWeatherReport(weatherReport)
                    .compose(schedulerProvider.ioToMainCompletableScheduler())
                    .subscribe({
                        getView()?.let {
                            it.hideProgress()
                            it.showInsertSuccessMessage()
                        }
                    }, { err ->
                        Log.e("onSubmitClick", err.message)
                        getView()?.let {
                            it.hideProgress()
                            it.showInsertFailedMessage()
                        }
                    })
            )
        }
    }

    override fun getCurrentWeather(latitude: Double, longitude: Double) {
        getView()?.showProgress()
        interactor?.let {
            compositeDisposable.add(
                it.getCurrentWeatherReport(longitude, latitude)
                    .compose(schedulerProvider.ioToMainSingleScheduler())
                    .subscribe({ response ->
                        Log.v("API response", response.toString())
                        getView()?.let {
                            val weatherReport = convertOpenWeatherResponseToWeatherReport((response))
                            it.hideProgress()
                            it.showCurrentWeather(weatherReport)
                        }
                    }, { err -> Log.e("API response", err.message) })
            )
        }
    }

    private fun convertOpenWeatherResponseToWeatherReport(weatherResponse: OpenWeatherResponse): WeatherReport {
        return WeatherReport(
            weatherResponse.id,
            weatherResponse.reportTimestamp,
            LocationInfo(weatherResponse.locationName, weatherResponse.locationInfo.countryCode, weatherResponse.locationInfo.sunriseTime, weatherResponse.locationInfo.sunsetTime),
            WeatherInfo(weatherResponse.weather[0].group, weatherResponse.weather[0].condition, weatherResponse.weather[0].iconId),
            Measurements(weatherResponse.measurements.temp, weatherResponse.measurements.tempMax, weatherResponse.measurements.tempMin, weatherResponse.measurements.humidity, weatherResponse.measurements.pressure, weatherResponse.clouds.cloudiness, weatherResponse.windInfo.deg, weatherResponse.windInfo.speed)
        )
    }
}
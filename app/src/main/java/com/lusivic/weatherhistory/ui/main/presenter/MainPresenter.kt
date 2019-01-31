package com.lusivic.weatherhistory.ui.main.presenter

import android.util.Log
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
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
            if(it.isLocationPermissionGranted()) it.setUp()
            else it.requestLocationPermission()
        }
    }

    override fun onLocationPermissionGranted() {
        getView()?.setUp()
    }

    override fun onLocationPermissionDenied() {
        getView()?.let{
            it.hideProgress()
            it.showPermissionDenied()
        }

    }

    override fun onViewReady() = getLatestWeatherReport()

    override fun onHistoryClick() {
        (getView() as MainActivity).openHistoryActivity()
    }

    override fun onRefreshClick() = getLatestWeatherReport()

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

    private fun getLatestWeatherReport() {
        getView()?.showProgress()
        interactor?.let {
            compositeDisposable.add(
                it.getCurrentWeatherReport()
                    .compose(schedulerProvider.ioToMainSingleScheduler())
                    .subscribe({ weatherReport ->
                        getView()?.let {
                            it.hideProgress()
                            it.showCurrentWeather(weatherReport)
                        }
                    }, { err -> Log.e("getLatestWeatherReport()", err.message) })
            )
        }
    }
    private fun convertOpenWeatherResponseToWeatherReport(weatherResponse: OpenWeatherResponse): WeatherReport {
        return WeatherReport(
            -1,
            weatherResponse.weather[0].main,
            weatherResponse.weather[0].description,
            CommonUtil.kelvinToCelsius(weatherResponse.main.temp).toFloat(),
            weatherResponse.main.humidity,
            weatherResponse.wind.speed.toFloat(),
            "${weatherResponse.name}, ${weatherResponse.sys.country}",
            weatherResponse.dt.toLong())
    }
}
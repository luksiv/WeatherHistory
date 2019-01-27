package com.lusivic.weatherhistory.ui.main.presenter

import android.util.Log
import com.lusivic.weatherhistory.ui.base.presenter.BasePresenter
import com.lusivic.weatherhistory.ui.main.interactor.MainMVPInteractor
import com.lusivic.weatherhistory.ui.main.view.MainActivity
import com.lusivic.weatherhistory.ui.main.view.MainMVPView
import com.lusivic.weatherhistory.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter<V : MainMVPView, I : MainMVPInteractor> @Inject internal constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) :
    BasePresenter<V, I>(
        interactor = interactor,
        schedulerProvider = schedulerProvider,
        compositeDisposable = disposable
    ), MainMVPPresenter<V, I> {
    override fun onAttach(view: V?) {
        super.onAttach(view)
        getLatestWeatherReport()
    }

    override fun getLatestWeatherReport() {
        getView()?.showProgress()
        interactor?.let {
            compositeDisposable.add(
                it.getCurrentWeatherReport()
                    .compose(schedulerProvider.ioToMainSingleScheduler())
                    .subscribe({ weatherReport ->
                        getView()?.let{
                            it.hideProgress()
                            it.showCurrentWeather(weatherReport)
                        }
                    }, { err -> Log.e("getLatestWeatherReport()", err.message) })
            )
        }
    }


    override fun onHistoryClick() {
        (getView() as MainActivity).openHistoryActivity()
    }

    override fun onRefreshClick() {
        getLatestWeatherReport()
    }

    override fun onSubmitClick() {
        TODO("save the weather report to database")
    }
}
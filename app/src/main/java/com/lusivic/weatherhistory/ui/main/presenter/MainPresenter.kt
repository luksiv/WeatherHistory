package com.lusivic.weatherhistory.ui.main.presenter

import com.lusivic.weatherhistory.ui.base.presenter.BasePresenter
import com.lusivic.weatherhistory.ui.main.interactor.MainMVPInteractor
import com.lusivic.weatherhistory.ui.main.view.MainActivity
import com.lusivic.weatherhistory.ui.main.view.MainMVPView
import com.lusivic.weatherhistory.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter<V : MainMVPView, I : MainMVPInteractor> @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) :
    BasePresenter<V, I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable), MainMVPPresenter<V, I> {
    override fun onAttach(view: V?) {
        super.onAttach(view)
        getLatestWeatherReport()
    }

    override fun getLatestWeatherReport(): Boolean? {
        getView()?.showProgress()
        val weatherReport = interactor?.getCurrentWeatherReport()
        getView()?.showCurrentWeather(weatherReport!!)
        getView()?.hideProgress()
        return true
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
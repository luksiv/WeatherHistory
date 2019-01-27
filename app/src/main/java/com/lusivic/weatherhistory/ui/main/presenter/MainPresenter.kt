package com.lusivic.weatherhistory.ui.main.presenter

import com.lusivic.weatherhistory.ui.base.presenter.BasePresenter
import com.lusivic.weatherhistory.ui.main.interactor.MainMVPInteractor
import com.lusivic.weatherhistory.ui.main.view.MainActivity
import com.lusivic.weatherhistory.ui.main.view.MainMVPView

class MainPresenter<V : MainMVPView, I : MainMVPInteractor> internal constructor(interactor: I) :
    BasePresenter<V, I>(interactor = interactor), MainMVPPresenter<V, I> {
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
package com.lusivic.weatherhistory.ui.main.presenter

import com.lusivic.weatherhistory.ui.base.presenter.MVPPresenter
import com.lusivic.weatherhistory.ui.main.interactor.IMainInteractor
import com.lusivic.weatherhistory.ui.main.view.IMainActivity

interface IMainPresenter<V : IMainActivity, I : IMainInteractor> : MVPPresenter<V, I> {
    fun getLatestWeatherReport(): Unit?
    fun onHistoryClick(): Unit?
    fun onRefreshClick(): Unit?
    fun onSubmitClick(): Unit?
}
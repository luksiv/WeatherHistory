package com.lusivic.weatherhistory.ui.main.presenter

import com.lusivic.weatherhistory.ui.base.presenter.MVPPresenter
import com.lusivic.weatherhistory.ui.main.interactor.MainMVPInteractor
import com.lusivic.weatherhistory.ui.main.view.MainMVPView

interface MainMVPPresenter<V : MainMVPView, I : MainMVPInteractor> : MVPPresenter<V, I> {
    fun getLatestWeatherReport(): Unit?
    fun onHistoryClick(): Unit?
    fun onRefreshClick(): Unit?
    fun onSubmitClick(): Unit?
}
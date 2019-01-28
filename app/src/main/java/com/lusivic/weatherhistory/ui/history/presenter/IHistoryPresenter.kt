package com.lusivic.weatherhistory.ui.history.presenter

import com.lusivic.weatherhistory.ui.base.presenter.MVPPresenter
import com.lusivic.weatherhistory.ui.history.interactor.IHistoryInteractor
import com.lusivic.weatherhistory.ui.history.view.IHistoryActivity

interface IHistoryPresenter<V: IHistoryActivity, I: IHistoryInteractor>: MVPPresenter<V,I> {
    fun getWeatherReportHistory()
    fun onBackPressed()
}
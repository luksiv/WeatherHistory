package com.lusivic.weatherhistory.ui.history.presenter

import android.util.Log
import com.lusivic.weatherhistory.ui.base.presenter.BasePresenter
import com.lusivic.weatherhistory.ui.history.interactor.IHistoryInteractor
import com.lusivic.weatherhistory.ui.history.view.IHistoryActivity
import com.lusivic.weatherhistory.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HistoryPresenter<V : IHistoryActivity, I : IHistoryInteractor> @Inject internal constructor(
    interactor: I,
    scheduler: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V, I>(interactor = interactor, schedulerProvider = scheduler, compositeDisposable = disposable),
    IHistoryPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        getView()?.setUp()
    }

    override fun onViewPrepared() {
        getWeatherReportHistory()
    }

    override fun getWeatherReportHistory() {
        getView()?.showProgress()
        interactor?.let {
            compositeDisposable.add(
                it.getWeatherReportHistory()
                    .compose(schedulerProvider.ioToMainSingleScheduler())
                    .subscribe({ weatherReportHistory ->
                        getView()?.let {
                            if(weatherReportHistory.isEmpty()) it.setListEmpty()
                            else it.updateHistoryAdapter(weatherReportHistory)
                            it.hideProgress()
                        }
                    }, {err -> Log.e("HistoryPresenter/getWeatherReportHistory", err.message)})
            )
        }
    }

    override fun onBackPressed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
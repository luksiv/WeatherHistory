package com.lusivic.weatherhistory.ui.history

import com.lusivic.weatherhistory.ui.history.interactor.HistoryInteractor
import com.lusivic.weatherhistory.ui.history.interactor.IHistoryInteractor
import com.lusivic.weatherhistory.ui.history.presenter.HistoryPresenter
import com.lusivic.weatherhistory.ui.history.presenter.IHistoryPresenter
import com.lusivic.weatherhistory.ui.history.view.HistoryAdapter
import com.lusivic.weatherhistory.ui.history.view.IHistoryActivity
import dagger.Module
import dagger.Provides

@Module
class HistoryActivityModule {

    @Provides
    internal fun provideHistoryInteractor(historyInteractor: HistoryInteractor): IHistoryInteractor = historyInteractor

    @Provides
    internal fun provideHistoryPresenter(historyPresenter: HistoryPresenter<IHistoryActivity, IHistoryInteractor>):
            IHistoryPresenter<IHistoryActivity, IHistoryInteractor> = historyPresenter

    @Provides
    internal fun provideHistoryAdapter(): HistoryAdapter = HistoryAdapter(ArrayList())
}
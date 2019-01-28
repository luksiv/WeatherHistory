package com.lusivic.weatherhistory.ui.main

import com.lusivic.weatherhistory.ui.main.interactor.MainInteractor
import com.lusivic.weatherhistory.ui.main.interactor.IMainInteractor
import com.lusivic.weatherhistory.ui.main.presenter.IMainPresenter
import com.lusivic.weatherhistory.ui.main.presenter.MainPresenter
import com.lusivic.weatherhistory.ui.main.view.IMainActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    internal fun provideMainInteractor(mainInteractor: MainInteractor): IMainInteractor = mainInteractor

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<IMainActivity, IMainInteractor>)
            : IMainPresenter<IMainActivity, IMainInteractor> = mainPresenter
}
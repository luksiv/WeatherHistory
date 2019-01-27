package com.lusivic.weatherhistory.ui.main

import com.lusivic.weatherhistory.ui.main.interactor.MainInteractor
import com.lusivic.weatherhistory.ui.main.interactor.MainMVPInteractor
import com.lusivic.weatherhistory.ui.main.presenter.MainMVPPresenter
import com.lusivic.weatherhistory.ui.main.presenter.MainPresenter
import com.lusivic.weatherhistory.ui.main.view.MainMVPView
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    internal fun provideMainInteractor(mainInteractor: MainInteractor): MainMVPInteractor = mainInteractor

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<MainMVPView, MainMVPInteractor>)
            : MainMVPPresenter<MainMVPView, MainMVPInteractor> = mainPresenter
}
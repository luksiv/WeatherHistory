package com.lusivic.weatherhistory.ui.main

import com.lusivic.weatherhistory.data.network.OpenWeatherApiService
import com.lusivic.weatherhistory.ui.main.interactor.IMainInteractor
import com.lusivic.weatherhistory.ui.main.interactor.MainInteractor
import com.lusivic.weatherhistory.ui.main.presenter.IMainPresenter
import com.lusivic.weatherhistory.ui.main.presenter.MainPresenter
import com.lusivic.weatherhistory.ui.main.view.IMainActivity
import com.lusivic.weatherhistory.utils.AppConstants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MainActivityModule {
    @Provides
    internal fun provideMainInteractor(mainInteractor: MainInteractor): IMainInteractor = mainInteractor

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<IMainActivity, IMainInteractor>)
            : IMainPresenter<IMainActivity, IMainInteractor> = mainPresenter

    @Provides
    internal fun provideOpenWeatherApiService(): OpenWeatherApiService = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(AppConstants.BASE_OW_API_URL)
        .build()
        .create(OpenWeatherApiService::class.java)
}
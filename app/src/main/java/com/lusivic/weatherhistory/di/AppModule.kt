package com.lusivic.weatherhistory.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.lusivic.weatherhistory.BuildConfig
import com.lusivic.weatherhistory.data.db.AppDatabase
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReportDao
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReportRepo
import com.lusivic.weatherhistory.utils.AppConstants
import com.lusivic.weatherhistory.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    companion object {
        const val OPEN_WEATHER_API_KEY = "OPEN_WEATHER_API_KEY"
    }

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.APP_DB_NAME).build()

    @Provides
    @Singleton
    internal fun provideWeatherReportDao(appDatabase: AppDatabase): WeatherReportDao = appDatabase.weatherReportDao()

    @Provides
    @Singleton
    internal fun provideWeatherReportRepo(weatherReportDao: WeatherReportDao): WeatherReportRepo = WeatherReportRepo(weatherReportDao)

    @Provides
    @Named(OPEN_WEATHER_API_KEY)
    internal fun provideOpenWeatherApiKey(): String = BuildConfig.OPEN_WEATHER_API_KEY

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()


}
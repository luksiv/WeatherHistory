package com.lusivic.weatherhistory.di

import com.lusivic.weatherhistory.ui.main.MainActivityModule
import com.lusivic.weatherhistory.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}
package com.lusivic.weatherhistory.ui.main.view

import android.os.Bundle
import com.lusivic.weatherhistory.R
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.ui.base.view.BaseActivity
import com.lusivic.weatherhistory.ui.main.interactor.MainInteractor
import com.lusivic.weatherhistory.ui.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity: BaseActivity(), MainMVPView {

    private val mMainInteractor: MainInteractor = MainInteractor()
    private val mMainPresenter: MainPresenter<MainActivity, MainInteractor> = MainPresenter(mMainInteractor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMainPresenter.onAttach(this)
    }

    override fun showCurrentWeather(weather: WeatherReport) {
        tv_temperature.text = "${weather.temperature}°C"
        tv_weatherName.text = weather.name
        tv_weatherDescription.text = weather.description
        tv_weatherDetails.text = "Humidity: ${weather.humidity}% Wind speed: ${weather.windSpeed} m/s"
    }

    override fun openHistoryActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

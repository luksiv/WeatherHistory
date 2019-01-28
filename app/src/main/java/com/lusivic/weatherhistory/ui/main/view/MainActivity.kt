package com.lusivic.weatherhistory.ui.main.view

import android.os.Bundle
import android.widget.Toast
import com.lusivic.weatherhistory.R
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.ui.base.view.BaseActivity
import com.lusivic.weatherhistory.ui.main.interactor.MainMVPInteractor
import com.lusivic.weatherhistory.ui.main.presenter.MainMVPPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity: BaseActivity(), MainMVPView {

    @Inject lateinit var mPresenter: MainMVPPresenter<MainMVPView, MainMVPInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        mPresenter.onAttach(this)
        setOnClickListeners()


    }

    override fun showCurrentWeather(weather: WeatherReport) {
        tv_temperature.text = "${weather.temperature}°C"
        tv_weatherName.text = weather.name
        tv_weatherDescription.text = weather.description
        tv_weatherDetails.text = "Humidity: ${weather.humidity}% Wind speed: ${weather.windSpeed} m/s"
    }

    override fun openHistoryActivity() {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }

    private fun setOnClickListeners(){
        btn_refresh.setOnClickListener {
            mPresenter.onRefreshClick()
        }
        btn_submit.setOnClickListener {
            mPresenter.onSubmitClick()
            Toast.makeText(this, "Submit not implemented!", Toast.LENGTH_LONG).show()
        }
        btn_history.setOnClickListener {
            mPresenter.onHistoryClick()
        }
    }
}

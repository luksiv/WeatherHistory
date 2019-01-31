package com.lusivic.weatherhistory.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.lusivic.weatherhistory.R
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.ui.base.view.BaseActivity
import com.lusivic.weatherhistory.ui.history.view.HistoryActivity
import com.lusivic.weatherhistory.ui.main.interactor.IMainInteractor
import com.lusivic.weatherhistory.ui.main.presenter.IMainPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), IMainActivity {

    @Inject
    lateinit var mPresenter: IMainPresenter<IMainActivity, IMainInteractor>

    private var mCurrentWeather: WeatherReport? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        mPresenter.onAttach(this)
        setOnClickListeners()
    }

    override fun showCurrentWeather(weather: WeatherReport) {
        mCurrentWeather = weather
        tv_temperature.text = "${weather.temperature}°C"
        tv_weatherName.text = weather.name
        tv_weatherDescription.text = weather.description
        tv_weatherDetails.text = "Humidity: ${weather.humidity}% Wind speed: ${weather.windSpeed} m/s"
    }

    override fun openHistoryActivity() {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }

    override fun showInsertSuccessMessage() {
        Toast.makeText(this, "Weather report submitted successfully!", Toast.LENGTH_LONG).show()
    }

    override fun showInsertFailedMessage() {
        Toast.makeText(this, "Failed to submit the weather report!", Toast.LENGTH_LONG).show()
    }

    private fun setOnClickListeners() {
        btn_refresh.setOnClickListener {
            mPresenter.onRefreshClick()
        }
        btn_submit.setOnClickListener {
            mCurrentWeather?.let { mPresenter.onSubmitClick(mCurrentWeather!!) }
        }
        btn_history.setOnClickListener {
            mPresenter.onHistoryClick()
        }
    }
}

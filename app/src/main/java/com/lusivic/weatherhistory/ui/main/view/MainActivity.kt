package com.lusivic.weatherhistory.ui.main.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.lusivic.weatherhistory.R
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.ui.base.view.BaseActivity
import com.lusivic.weatherhistory.ui.history.view.HistoryActivity
import com.lusivic.weatherhistory.ui.main.interactor.IMainInteractor
import com.lusivic.weatherhistory.ui.main.presenter.IMainPresenter
import com.lusivic.weatherhistory.utils.AppConstants
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject


class MainActivity : BaseActivity(), IMainActivity, LocationListener {

    @Inject
    lateinit var mPresenter: IMainPresenter<IMainActivity, IMainInteractor>
    @Inject
    lateinit var locationManager: LocationManager

    private var mCurrentWeather: WeatherReport? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        mPresenter.onAttach(this)
    }

    override fun setUp() {
        setOnClickListeners()
        mPresenter.onViewReady()
    }

    override fun isLocationPermissionGranted(): Boolean = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    override fun requestLocationPermission() = ActivityCompat.requestPermissions(
        this,
        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
        AppConstants.LOCATION_REQUEST_CODE
    )

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            AppConstants.LOCATION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission granted
                    mPresenter.onLocationPermissionGranted()
                } else {
                    // permission denied
                    mPresenter.onLocationPermissionDenied()
                }
            }
            else -> {
                // ignore other requests
            }
        }
    }

    override fun showPermissionDenied() {
        Toast.makeText(this, "Location permission is used for the app to work", Toast.LENGTH_LONG).show()
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

    override fun getCurrentLocation() {
        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (location != null && location.time > Calendar.getInstance().timeInMillis - 10 * 1000) {
            mPresenter.getCurrentWeather(location.longitude, location.latitude)
        } else {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                0f,
                this
            )
        }
    }

    override fun onLocationChanged(p0: Location?) {
        p0?.let {
            mPresenter.getCurrentWeather(it.longitude, it.latitude)
            locationManager.removeUpdates(this)
        }
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
    }

    override fun onProviderEnabled(p0: String?) {
    }

    override fun onProviderDisabled(p0: String?) {
    }

    private fun setOnClickListeners() {
        btn_refresh.setOnClickListener {
            mPresenter.onRefreshClick()
        }
        btn_submit.setOnClickListener {
            mCurrentWeather?.let { mPresenter.onSubmitClick(mCurrentWeather!!) } ?: mPresenter.onRefreshClick()
        }
        btn_history.setOnClickListener {
            mPresenter.onHistoryClick()
        }
    }
}

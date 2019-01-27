package com.lusivic.weatherhistory.ui.main.interactor

import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReportDao
import com.lusivic.weatherhistory.ui.base.interactor.BaseInteractor
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt

class MainInteractor @Inject internal constructor(weatherReportDao: WeatherReportDao): BaseInteractor(), MainMVPInteractor {
    override fun getCurrentWeatherReport(): WeatherReport {
        val temperature = ((Random().nextFloat() * 60 - 30) * 10).roundToInt().toFloat() / 10
        val name: String = when {
            temperature < -20 -> "Freezing"
            temperature > -20 && temperature < -10 -> "Very cold"
            temperature > -10 && temperature < 0 -> "Cold"
            temperature in 0..10 -> "Chilly"
            temperature in 11..20 -> "Warm"
            temperature > 20 -> "Hot"
            else -> "Don't know"
        }

        return WeatherReport(
            0,
            name,
            "Description",
            temperature,
            80,
            10.1f,
            "Vilnius",
            100020212
        )
    }
}
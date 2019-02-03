package com.lusivic.weatherhistory.data.db.weatherReport

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_reports")
data class WeatherReport(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "report_timestamp")
    val reportTimestamp: Int,

    @Embedded(prefix = "location_")
    val locationInfo: LocationInfo,

    @Embedded(prefix = "weather_")
    val weatherInfo: WeatherInfo,

    @Embedded(prefix = "measurements_")
    val measurements: Measurements

)
data class LocationInfo(
    val name: String,
    val countryCode: String,
    val sunriseTime: Int,
    val sunsetTime: Int
)

data class WeatherInfo(
    val group: String,
    val condition: String,
    val iconId: String
)

data class Measurements(
    val temp: Double,
    val tempMax: Double,
    val tempMin: Double,
    val humidity: Int,
    val pressure: Int,
    val cloudiness: Int,
    val windDeg: Int,
    val windSpeed: Double
)
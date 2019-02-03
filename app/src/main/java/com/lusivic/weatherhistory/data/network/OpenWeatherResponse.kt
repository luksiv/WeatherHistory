package com.lusivic.weatherhistory.data.network

import com.google.gson.annotations.SerializedName

data class OpenWeatherResponse(
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("dt")
    val reportTimestamp: Int,
    @SerializedName("name")
    val locationName: String,
    @SerializedName("sys")
    val locationInfo: Sys,
    @SerializedName("main")
    val measurements: Main,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("wind")
    val windInfo: Wind
) {
    data class Sys(
        @SerializedName("country")
        val countryCode: String,
        @SerializedName("sunrise")
        val sunriseTime: Int,
        @SerializedName("sunset")
        val sunsetTime: Int
    )

    data class Weather(
        @SerializedName("main")
        val group: String,
        @SerializedName("id")
        val conditionId: Int,
        @SerializedName("description")
        val condition: String,
        @SerializedName("icon")
        val iconId: String
    )

    data class Main(
        @SerializedName("temp")
        val temp: Double,
        @SerializedName("temp_max")
        val tempMax: Double,
        @SerializedName("temp_min")
        val tempMin: Double,
        @SerializedName("humidity")
        val humidity: Int,
        @SerializedName("pressure")
        val pressure: Double
    )

    data class Clouds(
        @SerializedName("all")
        val cloudiness: Int
    )

    data class Wind(
        @SerializedName("deg")
        val deg: Double,
        @SerializedName("speed")
        val speed: Double
    )
}
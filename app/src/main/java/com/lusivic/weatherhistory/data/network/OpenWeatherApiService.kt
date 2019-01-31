package com.lusivic.weatherhistory.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

// https://api.openweathermap.org/data/2.5/weather?lat=54.899138&lon=23.976159&appid=63cc0e61413bac6ec26e145f62c3edfe
interface OpenWeatherApiService {
    @GET("data/2.5/weather")
    fun getCurrentWeatherByCoord(@Query("lat") latitude: Float,
                                 @Query("lon") longitude: Float,
                                 @Query("appid") apiKey: String) : Single<OpenWeatherResponse>
}
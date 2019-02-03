package com.lusivic.weatherhistory.data.db.weatherReport

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherReportDao {

    @Insert
    fun insert(weatherReport: WeatherReport)

    @Delete
    fun delete(weatherReport: WeatherReport)

    @Query("SELECT * FROM weather_reports ORDER BY report_timestamp DESC")
    fun selectAll(): List<WeatherReport>

}
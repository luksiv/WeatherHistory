package com.lusivic.weatherhistory.data.db.weatherReport

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_reports")
data class WeatherReport(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "temperature")
    var temperature: Float,
    @ColumnInfo(name = "humidity")
    var humidity: Int,
    @ColumnInfo(name = "wind_speed")
    var windSpeed: Float,
    @ColumnInfo(name = "location")
    var location: String,
    @ColumnInfo(name = "report_timestamp")
    var timestamp: Long
)

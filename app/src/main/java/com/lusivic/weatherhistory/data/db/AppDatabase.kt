package com.lusivic.weatherhistory.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReportDao

@Database(entities = [WeatherReport::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherReportDao(): WeatherReportDao
}
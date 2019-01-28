package com.lusivic.weatherhistory.ui.history.view

import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.ui.base.view.MVPView

interface IHistoryActivity : MVPView{
    fun setUp()
    fun updateHistoryAdapter(history: List<WeatherReport>)
    fun setListEmpty()
    fun goBack()
}
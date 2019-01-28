package com.lusivic.weatherhistory.ui.history.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lusivic.weatherhistory.R
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import kotlinx.android.synthetic.main.item_history_list.view.*

class HistoryAdapter(private val weatherHistoryList: MutableList<WeatherReport>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HistoryViewHolder(
        LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_history_list, parent, false)
    )

    override fun getItemCount() = this.weatherHistoryList.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) = holder.let {
        it.clear()
        it.onBind(position)
    }

    internal fun addReportsToList(reports: List<WeatherReport>) {
        this.weatherHistoryList.addAll(reports)
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun clear() {
            itemView.tv_history_item_date.text = ""
            itemView.tv_history_item_location.text = ""
            itemView.tv_history_item_temperature.text = ""
            itemView.tv_history_item_description.text = ""
            itemView.tv_history_item_details.text = ""
        }

        fun onBind(position: Int) {
            val date = weatherHistoryList[position].timestamp.toString()
            val location = weatherHistoryList[position].location
            val temperature = "Temperature: ${weatherHistoryList[position].temperature}°C"
            val description = "${weatherHistoryList[position].name} (${weatherHistoryList[position].description})"
            val details =
                "Humidity: ${weatherHistoryList[position].humidity}% Wind speed: ${weatherHistoryList[position].windSpeed} m/s"

            inflateData(date, location, temperature, description, details)

        }

        private fun inflateData(
            date: String,
            location: String,
            temperature: String,
            description: String,
            details: String
        ) {
            itemView.tv_history_item_date.text = date
            itemView.tv_history_item_location.text = location
            itemView.tv_history_item_temperature.text = temperature
            itemView.tv_history_item_description.text = description
            itemView.tv_history_item_details.text = details
        }

    }
}
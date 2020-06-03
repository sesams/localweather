package com.ks.idustest.view.adapter

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ks.idustest.R
import com.ks.idustest.data.Weather
import com.ks.idustest.data.WeatherInfo
import com.ks.idustest.extension.getImageUrl
import kotlinx.android.synthetic.main.list_item_item.view.*
import kotlinx.android.synthetic.main.weather_detail.view.*

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }

    private var list = mutableListOf<Weather>()

    fun updateList(new: List<Weather>) {
        list.clear()
        list.addAll(new)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_ITEM
    }

    override fun getItemCount(): Int {
        return if (list.isEmpty()) 0 else list.size + 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == TYPE_HEADER)
            ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_head, parent, false)
            )
        else
            ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_item, parent, false)
            )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position != TYPE_HEADER)
            holder.bind(list[position - 1])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(weather: Weather) {
            clear()
            with(itemView) {
                title.text = weather.title
                bindWeatherDetail(todayContainer, weather.weatherInfo?.get(0))
                bindWeatherDetail(tomorrowContainer, weather.weatherInfo?.get(1))
            }
        }

        private fun clear() {
            with(itemView) {
                description.text = ""
                temperature.text = ""
                humidity.text = ""
                icon.setImageDrawable(null)
            }
        }

        private fun bindWeatherDetail(view: View, weatherInfo: WeatherInfo?) {
            val getSpanString: (string: String, length: Int) -> Spannable = { string, length ->
                SpannableStringBuilder(string).apply {
                    setSpan(StyleSpan(Typeface.BOLD), 0, length, SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }

            weatherInfo?.let {
                with(view) {
                    description.text = it.weather_state_name
                    temperature.text = getSpanString(
                        context.getString(R.string.temperature, it.the_temp.toInt()),
                        it.the_temp.toInt().toString().length
                    )
                    humidity.text = getSpanString(
                        context.getString(R.string.percent, it.humidity),
                        it.humidity.toString().length
                    )
                    Glide.with(itemView.context).load(it.getImageUrl()).into(icon)
                }
            }
        }
    }
}

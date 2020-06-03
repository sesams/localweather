package com.ks.idustest.extension

import android.text.format.DateUtils
import com.ks.idustest.api.BASE_URL
import com.ks.idustest.data.WeatherInfo
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): Date? {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
}

fun Date.isToday(): Boolean = DateUtils.isToday(this.time)

fun Date.isTomorrow(): Boolean = DateUtils.isToday(this.time - DateUtils.DAY_IN_MILLIS)

fun WeatherInfo.getImageUrl(): String =
    "${BASE_URL}static/img/weather/png/64/${this.weather_state_abbr}.png"
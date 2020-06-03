package com.ks.idustest.api.response.mapper

import com.ks.idustest.api.response.LocationResponse
import com.ks.idustest.api.response.WeatherResponse
import com.ks.idustest.data.LocationInfo
import com.ks.idustest.data.WeatherInfo
import com.ks.idustest.extension.isToday
import com.ks.idustest.extension.isTomorrow
import com.ks.idustest.extension.toDate

object ResponseMapper {

    fun convertToLocationInfo(response: LocationResponse): LocationInfo {
        val latlon = response.lattLong?.split(",")
        return LocationInfo(
            response.title ?: "",
            response.locationType ?: "",
            response.woeid ?: 0,
            latlon?.get(0)?.toDouble() ?: 0.0,
            latlon?.get(1)?.toDouble() ?: 0.0
        )
    }

    fun convertToWeather(response: WeatherResponse): List<WeatherInfo> {
        return response.consolidatedWeather
            ?.filter {
                val date = it.applicableDate?.toDate()
                date != null && (date.isToday() || date.isTomorrow())
            }
            ?.map {
                WeatherInfo(
                    it.weatherStateName ?: "",
                    it.weatherStateAbbr ?: "",
                    it.theTemp ?: Float.MAX_VALUE,
                    it.humidity ?: Int.MAX_VALUE
                )
            } ?: emptyList()
    }
}
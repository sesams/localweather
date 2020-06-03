package com.ks.idustest.repository

import com.ks.idustest.data.LocationInfo
import com.ks.idustest.data.WeatherInfo

interface WeatherRepository {
    fun getLocation(keyword: String): List<LocationInfo>
    fun getWeather(id: Int, f: (id: Int, weatherInfo: List<WeatherInfo>) -> Unit)
}

interface WeatherCache {
    fun isContains(id: Int): Boolean
    fun put(id: Int, weather: List<WeatherInfo>): Boolean
    fun get(id: Int): List<WeatherInfo>?
}
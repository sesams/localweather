package com.ks.idustest.repository

import com.ks.idustest.api.WeatherApi
import com.ks.idustest.api.WeatherApiImpl
import com.ks.idustest.data.LocationInfo
import com.ks.idustest.data.WeatherInfo
import java.util.*

class WeatherRepositoryImpl : WeatherRepository {

    private val api: WeatherApi = WeatherApiImpl()

    override fun getLocation(keyword: String): List<LocationInfo> {
        return try {
            api.getLocation(keyword)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override fun getWeather(id: Int, f: (id: Int, weatherInfo: List<WeatherInfo>) -> Unit) {
        val result: List<WeatherInfo> = try {
            api.getWeather(id)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
        f(id, result)
    }
}

class WeatherCacheImpl : WeatherCache {

    private val hashMap = WeakHashMap<Int, List<WeatherInfo>>()

    override fun isContains(id: Int): Boolean {
        return hashMap.containsKey(id)
    }

    override fun put(id: Int, weather: List<WeatherInfo>): Boolean {
        if (!hashMap.containsKey(id))
            hashMap[id] = weather

        return true
    }

    override fun get(id: Int): List<WeatherInfo>? {
        return hashMap[id]
    }
}

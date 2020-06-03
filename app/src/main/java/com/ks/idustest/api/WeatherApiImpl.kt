package com.ks.idustest.api

import com.ks.idustest.api.exception.LocationException
import com.ks.idustest.api.exception.WeatherException
import com.ks.idustest.api.response.mapper.ResponseMapper
import com.ks.idustest.data.LocationInfo
import com.ks.idustest.data.WeatherInfo
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WeatherApiImpl : WeatherApi {

    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(WeatherService::class.java)

    override fun getLocation(keyword: String): List<LocationInfo> {
        val result = service.getLocation(keyword).execute()
        if (!result.isSuccessful || result.body() == null) {
            throw LocationException("Location Api is not success!")
        }
        return result.body()!!.map { ResponseMapper.convertToLocationInfo(it) }
    }

    override fun getWeather(id: Int): List<WeatherInfo> {
        val result = service.getWeather(id).execute()
        if (!result.isSuccessful || result.body() == null) {
            throw WeatherException("Weather Api is not success!")
        }
        return ResponseMapper.convertToWeather(result.body()!!)
    }
}
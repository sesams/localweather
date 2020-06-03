package com.ks.idustest.api

import com.ks.idustest.api.response.LocationResponse
import com.ks.idustest.api.response.WeatherResponse
import com.ks.idustest.data.LocationInfo
import com.ks.idustest.data.WeatherInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://www.metaweather.com/"

interface WeatherService {

    @GET("api/location/search/")
    fun getLocation(@Query("query") keyword: String): Call<List<LocationResponse>>

    @GET("api/location/{id}/")
    fun getWeather(@Path("id") id: Int): Call<WeatherResponse>
}

interface WeatherApi {
    fun getLocation(keyword: String): List<LocationInfo>
    fun getWeather(id: Int): List<WeatherInfo>
}
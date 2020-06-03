package com.ks.idustest.data

data class LocationInfo(
    val title: String,
    val locationType: String,
    val woeid: Int,
    val lat: Double,
    val lon: Double
)

data class WeatherInfo(
    val weather_state_name: String,
    val weather_state_abbr: String,
    val the_temp: Float,
    val humidity: Int
)

data class Weather(
    val id: Int,
    val title: String,
    var weatherInfo: List<WeatherInfo>? = null
)
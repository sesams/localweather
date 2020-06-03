package com.ks.idustest.api.response

import com.google.gson.annotations.SerializedName


class WeatherResponse {
    @SerializedName("consolidated_weather")
    var consolidatedWeather: List<ConsolidatedWeather>? = null

    @SerializedName("time")
    var time: String? = null

    @SerializedName("sun_rise")
    var sunRise: String? = null

    @SerializedName("sun_set")
    var sunSet: String? = null

    @SerializedName("timezone_name")
    var timezoneName: String? = null

    @SerializedName("parent")
    var parent: Parent? = null

    @SerializedName("sources")
    var sources: List<Source>? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("location_type")
    var locationType: String? = null

    @SerializedName("woeid")
    var woeid: Int? = null

    @SerializedName("latt_long")
    var lattLong: String? = null

    @SerializedName("timezone")
    var timezone: String? = null
}

class ConsolidatedWeather {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("weather_state_name")
    var weatherStateName: String? = null

    @SerializedName("weather_state_abbr")
    var weatherStateAbbr: String? = null

    @SerializedName("wind_direction_compass")
    var windDirectionCompass: String? = null

    @SerializedName("created")
    var created: String? = null

    @SerializedName("applicable_date")
    var applicableDate: String? = null

    @SerializedName("min_temp")
    var minTemp: Float? = null

    @SerializedName("max_temp")
    var maxTemp: Float? = null

    @SerializedName("the_temp")
    var theTemp: Float? = null

    @SerializedName("wind_speed")
    var windSpeed: Float? = null

    @SerializedName("wind_direction")
    var windDirection: Float? = null

    @SerializedName("air_pressure")
    var airPressure: Float? = null

    @SerializedName("humidity")
    var humidity: Int? = null

    @SerializedName("visibility")
    var visibility: Float? = null

    @SerializedName("predictability")
    var predictability: Int? = null
}

class Parent {
    @SerializedName("title")
    var title: String? = null

    @SerializedName("location_type")
    var locationType: String? = null

    @SerializedName("woeid")
    var woeid: Int? = null

    @SerializedName("latt_long")
    var lattLong: String? = null
}

class Source {
    @SerializedName("title")
    var title: String? = null

    @SerializedName("slug")
    var slug: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("crawl_rate")
    var crawlRate: Int? = null
}

package com.ks.idustest

import com.ks.idustest.api.WeatherApi
import com.ks.idustest.api.WeatherApiImpl
import com.ks.idustest.data.LocationInfo
import com.ks.idustest.data.WeatherInfo
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ApiTest {

    @Test
    fun test_remote_data_store() {
        val keyword = "Se"
        val weatherApi = MockApi()
        //val weatherApi = WeatherApiImpl()
        val list = weatherApi.getLocation(keyword)

        println(list.joinToString("\n"))

        assertNotNull(list)
        assertTrue(list.isNotEmpty())
        assertTrue(list.first().title.toLowerCase().contains(keyword.toLowerCase()))

        println()

        list.forEach {
            val weather = weatherApi.getWeather(it.woeid)
            println(weather.joinToString("\n"))
            println()

            assertNotNull(weather)
            assertTrue(weather.isNotEmpty())
            assertNotNull(weather.first().the_temp)
        }
    }

    class MockApi : WeatherApi {
        override fun getLocation(keyword: String): List<LocationInfo> {
            return arrayListOf(
                LocationInfo("seoul", "city", 12312, 123123.1213, 123123.123),
                LocationInfo("seoul", "city", 12312, 123123.1213, 123123.123)
            )
        }

        override fun getWeather(id: Int): List<WeatherInfo> {
            return arrayListOf(
                WeatherInfo("Clear", "c", 10.0f, 61),
                WeatherInfo("Clear", "c", 10.0f, 61)
            )
        }
    }
}

package com.ks.idustest

import com.ks.idustest.data.LocationInfo
import com.ks.idustest.data.WeatherInfo
import com.ks.idustest.repository.WeatherRepository
import org.junit.Assert.assertTrue
import org.junit.Test

class RepositoryTest {

    @Test
    fun test_repository() {
        val keyword = "Se"
        val repository = MockRepository()

        val location = repository.getLocation(keyword)
        assertTrue(location.isNotEmpty())

        location.forEach {
            repository.getWeather(it.woeid) { id, weatherInfo ->
                assertTrue(weatherInfo.isNotEmpty())
                println("${it.title} ${weatherInfo.joinToString("\n")}")
            }
        }
    }

    class MockRepository : WeatherRepository {

        private val cache = CacheTest.MockCache()
        private val api = ApiTest.MockApi()

        override fun getLocation(keyword: String): List<LocationInfo> {
            return api.getLocation(keyword)
        }

        override fun getWeather(id: Int, f: (id: Int, weatherInfo: List<WeatherInfo>) -> Unit) {
            f(id, api.getWeather(id))
        }
    }
}
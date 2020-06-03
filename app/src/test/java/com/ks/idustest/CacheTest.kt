package com.ks.idustest

import com.ks.idustest.data.Weather
import com.ks.idustest.repository.WeatherCache
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*

class CacheTest {

    @Test
    fun test_cache() {
        val keyword = "Se"
        val cache = MockCache()
        val api = ApiTest.MockApi()

        val list = mutableListOf<Weather>()
        api.getLocation(keyword).forEach { location ->
            list.add(Weather(location.woeid, location.title, api.getWeather(location.woeid)))
        }

        assertTrue(cache.put(keyword, list))
        assertTrue(cache.isContains(keyword))
        assertNotNull(cache.get(keyword))
    }

    class MockCache : WeatherCache {

        private val hashMap = WeakHashMap<String, List<Weather>>()

        override fun isContains(keyword: String): Boolean {
            return hashMap.contains(keyword)
        }

        override fun put(keyword: String, weather: List<Weather>): Boolean {
            if (!hashMap.containsKey(keyword))
                hashMap[keyword] = weather

            return true
        }

        override fun get(keyword: String): List<Weather>? {
            return hashMap[keyword]
        }
    }
}
package com.ks.idustest.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ks.idustest.data.Weather
import com.ks.idustest.repository.WeatherRepository
import com.ks.idustest.repository.WeatherRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val repository: WeatherRepository = WeatherRepositoryImpl()

    val weather = MutableLiveData<List<Weather>>()

    fun requestWeather(keyword: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = mutableListOf<Weather>()
            repository.getLocation(keyword).forEach { location ->
                result.add(Weather(location.woeid, location.title))

                CoroutineScope(Dispatchers.IO).launch {
                    repository.getWeather(location.woeid) { id, list ->
                        result.first { it.id == id }.weatherInfo = list

                        CoroutineScope(Dispatchers.Main).launch {
                            weather.value = result
                        }
                    }
                }
            }
        }
    }
}
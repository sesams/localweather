package com.ks.idustest.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ks.idustest.R
import com.ks.idustest.view.adapter.WeatherAdapter
import com.ks.idustest.view.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private val adapter = WeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {
        refresh.setOnRefreshListener {
            adapter.updateList(emptyList())
            loadWeather()
        }

        recyclerView.adapter = adapter

        viewModel.weather.observe(this, Observer {
            adapter.updateList(it)

            if (progress.visibility == View.VISIBLE)
                progress.visibility = View.GONE

            if (refresh.isRefreshing)
                refresh.isRefreshing = false
        })

        loadWeather()
    }

    private fun loadWeather() = viewModel.requestWeather("se")
}

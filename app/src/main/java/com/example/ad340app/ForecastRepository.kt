package com.example.ad340app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepository {
    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast

    fun loadForecast(zipcode: String) {
        val randomValues = List(10) { Random.nextFloat().rem(100) * 100 }
        val forecastItems = randomValues.map { temp ->
            DailyForecast(temp, getTempDescription(temp))
        }
    _weeklyForecast.setValue(forecastItems)
    }

    private fun getTempDescription(temp: Float) : String {
        return when (temp) {
            in Float.MIN_VALUE.rangeTo(32f) -> "Freezing"
            in 32f.rangeTo(50f) -> "Cold"
            in 50f.rangeTo(80f) -> "Normal"
            in 80f.rangeTo(100f) -> "Hot"
            in 100f.rangeTo(Float.MAX_VALUE) -> "Hot AF"
            else -> "Does not compute"
        }
    }
}
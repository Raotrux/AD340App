package com.example.ad340app.api

import com.squareup.moshi.Json
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class Forecast(val temp: Float)
data class Coordinates(val lat: Float, val lon: Float)

data class CurrentWeather(
    val name: String,
    val coord: Coordinates,
    @field:Json(name = "main") val forecast: Forecast
)

interface OpenWeatherMapServer {

}
package com.example.ad340app

interface AppNavigator {
    fun navigateToCurrentForecast(zipcode: String)

    fun navigateToLocationEntry()
}
package com.example.myapplication

import com.squareup.moshi.Json

data class ForecastWeather(
    val name: String,
    val list: List<ForecastWeatherItem>,
)

data class ForecastWeatherItem(
    val sunrise: Double,
    val sunset: Double,
    val temp: ForecastWeatherItem,
    @Json(name = "feels_like")
    val feelsLike: ForecastWeatherFeelsLike,


)

data class ForecastWeatherTemp(
    val day: Float,
    val min: Float,
    val max: Float,
)
 data class ForecastWeatherFeelsLike(
     val day: Float,
 )
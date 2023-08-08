package com.example.myapplication

import com.squareup.moshi.Json

data class ForecastWeather(
//    val name: String,
    val list: List<ForecastWeatherItem>,
)

data class ForecastWeatherItem(
    val sunrise: Long,
    val sunset: Long,
    val temp: ForecastWeatherTemp,
    @Json(name = "feels_like")
    val feelsLike: ForecastWeatherFeelsLike,
    val weather: List<WeatherListItem>


)

data class ForecastWeatherTemp(
    val day: Float,
    val min: Float,
    val max: Float,
)
 data class ForecastWeatherFeelsLike(
     val day: Float,
 )
package com.example.myapplication

import com.squareup.moshi.Json

data class CurrentWeather(
    val name: String,
    val main: WeatherObject

)

data class WeatherObject(
    val temp: Float,
    @Json(name= "feels_like")
    val feelsLike: Float,
    @Json(name="temp_min")
    val lowTemp: Float,
    @Json(name="temp_max")
    val highTemp: Float,
    val humidity: Int,
    val pressure: Float,
)
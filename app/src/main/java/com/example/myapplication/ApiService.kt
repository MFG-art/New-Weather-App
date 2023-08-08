package com.example.myapplication

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/data/2.5/weather?&units=imperial&appid=2bfdcae4c4869dd4b000b9a614fbd601")

    suspend fun getWeatherData(@Query("zip") zipCode: Int): CurrentWeather

    @GET("/data/2.5/forecast/daily?&units=imperial&cnt=16&appid=2bfdcae4c4869dd4b000b9a614fbd601")
    suspend fun getForecastData(@Query("zip") zipCode: Int): ForecastWeather
}

package com.example.myapplication

import retrofit2.http.GET

interface ApiService {

    @GET("/data/2.5/weather?zip=55075&units=imperial&appid=2bfdcae4c4869dd4b000b9a614fbd601")
    suspend fun getWeatherData(): CurrentWeather

    @GET("/data/2.5/forecast/daily?&units=imperial&zip=55075&cnt=16&appid=2bfdcae4c4869dd4b000b9a614fbd601")
    suspend fun getForecastData(): ForecastWeather
}

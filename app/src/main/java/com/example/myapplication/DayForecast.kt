package com.example.myapplication


data class DayForecast(
        val date: Long,
        val sunrise: Long,
        val sunset:Long,
        val temp:ForecastTemp,
        val pressure:Float,
        val humidity:Int,
)

data class ForecastTemp (
        val day: Float,
        val max:Float,
        val min:Float,
        )

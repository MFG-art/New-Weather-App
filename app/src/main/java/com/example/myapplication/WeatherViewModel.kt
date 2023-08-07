package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {

    private val _weatherData: MutableLiveData<CurrentWeather> = MutableLiveData()
    val weatherData: LiveData<CurrentWeather>
        get() = _weatherData


    fun viewAppeared(zipCode: Int) = viewModelScope.launch {
        try {
            _weatherData.value = apiService.getWeatherData(zipCode)
        } catch (e: HttpException) {

        }
    }
}

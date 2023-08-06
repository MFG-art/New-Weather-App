package com.example.myapplication

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "CurrentWeather"){
                        composable("CurrentWeather") {WeatherView(navController)}
                        composable("ForecastScreen") { ForecastView(navController)}
                    }
                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastView(
    navController: NavController,
    viewModel: ForecastViewModel = hiltViewModel(),

){
    val forecastData = viewModel.weatherData.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.viewAppeared()
    }

    ForecastScreen(navController = navController, forecastData)
}

@Composable
fun WeatherView(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherData = viewModel.weatherData.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.viewAppeared()
    }

    MainScreen(navController = navController, weatherData)

}

@Composable
fun WeatherConditionIcon(
    url: String
) {
    AsyncImage(model = url, contentDescription = "")
}

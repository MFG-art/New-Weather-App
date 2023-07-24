package com.example.myapplication

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.text.SimpleDateFormat




fun timestampToDateString(timestamp: Long): String {
    val formatter = SimpleDateFormat("MMM dd")
    return formatter.format(timestamp * 1000L)
}


fun timestampToTimeString(timestamp: Long): String {
    val formatter = SimpleDateFormat("h:mm a")
    return formatter.format(timestamp * 1000L)
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastScreen(navController: NavController, forecastData: State<ForecastWeather?>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Forecast",

                        )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Gray,
                    titleContentColor = Color.White,
                ),

                )
        }, content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color(0xffffffff)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DataItemList(navController,forecastData)
            }
        })
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DataItemList(navController: NavController, forecastItems: State<ForecastWeather?>) {
    LazyColumn {
        forecastItems.let {
            forecastItems?.value?.let { it ->
                items(it.list) { forecastWeatherItem ->
                        ForecastItemView(forecastWeatherItem)
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastItemView(forecastWeatherItem: ForecastWeatherItem) {
    /* Create the view for the data item here. */
    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = R.drawable.sun),
            contentDescription = stringResource(id = R.string.sun_string)
        )
        Box(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(text = timestampToDateString(forecastWeatherItem.sunrise))
        }
        Spacer(modifier = Modifier.size(30.dp))
        Column() {
            Text(text = "Temp: " + forecastWeatherItem.temp.day + "°")
            Text(text = "High: " + forecastWeatherItem.temp.max + "°")
        }
        Column() {
            Text(text = "")
            Text(text = "Low: " + forecastWeatherItem.temp.min + "°")
        }
        Spacer(modifier = Modifier.size(30.dp))
        Column() {
            Text(text = "Sunrise: " + timestampToTimeString(forecastWeatherItem.sunrise))
            Text(text = "Sunset: " + timestampToTimeString(forecastWeatherItem.sunset))
        }

    }
    Spacer(modifier = Modifier.size(10.dp))


}





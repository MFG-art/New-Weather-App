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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import java.text.SimpleDateFormat




fun timestampToDateString(timestamp: Long): String {
    val formatter = SimpleDateFormat("MMM dd")
    return formatter.format(timestamp * 1000L)
}


fun timestampToTimeString(timestamp: Long): String {
    val formatter = SimpleDateFormat("h:mm a")
    return formatter.format(timestamp * 1000L)
}

fun appendStrings(s1: String, s2: String): String {
    return s1 + s2
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastScreen(navController: NavController,  forecastData: State<ForecastWeather?>) {
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
                    Spacer(modifier = Modifier.size(20.dp))
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
        Spacer(modifier = Modifier.size(10.dp))
        Column() {

            AsyncImage(
                model = "https://openweathermap.org/img/wn/" + forecastWeatherItem.weather?.get(0)?.icon.toString() +"@2x.png",
                contentDescription = null,
            )


        }
        Text(text = timestampToDateString(forecastWeatherItem.sunrise), style= MaterialTheme.typography.labelLarge)
        Column() {
            Text(text =  stringResource(id = R.string.temperature) +
                    forecastWeatherItem.temp.day +
                    stringResource(id = R.string.degrees),
                style= MaterialTheme.typography.bodySmall)
            Text(text = stringResource(id = R.string.high_temp) +
                    forecastWeatherItem.temp.max +
                    stringResource(id = R.string.degrees),
                style= MaterialTheme.typography.bodySmall)
        }
        Spacer(modifier = Modifier.size(20.dp))
        Column() {
            Text(text = stringResource(id = R.string.empty_string), style= MaterialTheme.typography.bodySmall)
            Text(text = stringResource(id = R.string.low_temp)
                    + forecastWeatherItem.temp.min
                    + stringResource(id = R.string.degrees),
                style= MaterialTheme.typography.bodySmall)
        }
        Spacer(modifier = Modifier.size(20.dp))
        Column() {
            Text(text = stringResource(id = R.string.sunrise) + timestampToTimeString(forecastWeatherItem.sunrise), style= MaterialTheme.typography.bodySmall)
            Text(text = stringResource(id = R.string.sunset) + timestampToTimeString(forecastWeatherItem.sunset), style= MaterialTheme.typography.bodySmall)
        }

    }
    Spacer(modifier = Modifier.size(30.dp))


}





package com.example.myapplication

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, weatherData : State<CurrentWeather?>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mauricio's Weather App",

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
                Text(
                    text = stringResource(id = R.string.location),
                    fontSize = 18.sp,
                    color = Color.Black,
//                    layout_marginTop = 10.dp,
                    modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)
                )
                Row() {
                    Column() {
                        Text(
                            text = stringResource(id = R.string.temperature),
                            fontSize = 60.sp,
                            color = Color.Black,
                        )
                        Text(
                            text = stringResource(id = R.string.feels_like),
                            fontSize = 20.sp,
                            color = Color.Black,
                        )
                    }

                    Image(
                        modifier = Modifier.size(150.dp),
                        painter = painterResource(id = R.drawable.sun),
                        contentDescription = stringResource(id = R.string.sun_string)
                    )

                }


                Text(
                    text = stringResource(id = R.string.low_temp),
                    fontSize = 20.sp,
                    color = Color.Black,
                )
                Text(
                    text = stringResource(id = R.string.high_temp),
                    fontSize = 20.sp,
                    color = Color.Black,
                )

                Text(
                    text = stringResource(id = R.string.humidity),
                    fontSize = 20.sp,
                    color = Color.Black,
                )
                Text(
                    text = stringResource(id = R.string.pressure),
                    fontSize = 20.sp,
                    color = Color.Black,
                )
                Button(onClick = {
                    navController.navigate("ForecastScreen.kt")
                }) {
                    Text(text = "Forecast")
                }
            }
        })
}
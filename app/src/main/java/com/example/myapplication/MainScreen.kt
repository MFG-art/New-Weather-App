package com.example.myapplication

import android.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, zipCode: MutableState<Int>,viewModel: WeatherViewModel) {
    var textBoxInput by remember { mutableStateOf("") }
    val weatherData = viewModel.weatherData.observeAsState()
    var showZipCodeAlert by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),

                        )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Gray,
                    titleContentColor = Color.White,
                ),

                )
        }, content = {
            if (showZipCodeAlert) {
                zipCodeAlert(onConfirm = { showZipCodeAlert = false}, onCancel = {showZipCodeAlert = false})
            }
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color(0xffffffff)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(value = textBoxInput, onValueChange = { newText: String -> textBoxInput = newText})
                Button(onClick = {

                    if (textBoxInput.length == 5) {
                        try {
                            viewModel.viewAppeared(
                                textBoxInput.toInt()
                            )
                            zipCode.value = textBoxInput.toInt()
                        } catch (e: NumberFormatException) {
                            showZipCodeAlert = true;
                        }
                    }
                    showZipCodeAlert = true;

                }) {
                    Text(text = stringResource(id = R.string.search_btn))
                }
                Text(
                    text = weatherData.value?.name.toString(),
                    fontSize = 18.sp,
                    color = Color.Black,
//                    layout_marginTop = 10.dp,
                    modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)
                )
                Row() {
                    Column() {
                        Text(
//
                            text = stringResource(id = R.string.temperature) + weatherData.value?.main?.temp.toString() + stringResource(id = R.string.degrees),
                            fontSize = 30.sp,
                            color = Color.Black,
                        )
                        Text(
                            text = stringResource(id = R.string.feels_like) + weatherData.value?.main?.feelsLike.toString() + stringResource(id = R.string.degrees),
                            fontSize = 20.sp,
                            color = Color.Black,
                        )
                    }

//                    Image(
//                        modifier = Modifier.size(150.dp),
//                        painter = painterResource(id = R.drawable.sun),
//                        contentDescription = stringResource(id = R.string.sun_string)
//                    )
                    AsyncImage(
                        model = "https://openweathermap.org/img/wn/" + weatherData.value?.weather?.get(0)?.icon.toString() + "@4x.png",
                        contentDescription = null,
                    )

                }


                Text(
                    text = stringResource(id = R.string.low_temp) +  weatherData.value?.main?.lowTemp.toString() + stringResource(id = R.string.degrees),
                    fontSize = 20.sp,
                    color = Color.Black,
                )
                Text(
                    text = stringResource(id = R.string.high_temp) + weatherData.value?.main?.highTemp.toString() + stringResource(id = R.string.degrees),
                    fontSize = 20.sp,
                    color = Color.Black,
                )

                Text(
                    text = stringResource(id = R.string.humidity) + weatherData.value?.main?.humidity.toString(),
                    fontSize = 20.sp,
                    color = Color.Black,
                )
                Text(
                    text = stringResource(id = R.string.pressure) + weatherData.value?.main?.pressure.toString() + stringResource(
                        id = R.string.pressure_unit
                    ),
                    fontSize = 20.sp,
                    color = Color.Black,
                )
                Button(onClick = {
                    navController.navigate("ForecastScreen/" + zipCode.value.toString())
                }) {
                    Text(text = stringResource(id = R.string.btn_text))
                }
            }
        })
}

@Composable
private fun zipCodeAlert(
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = @Composable {
            Button(onClick = { onConfirm() }) {
                Text("OK")
            }
        },
        dismissButton = @Composable {
            Button(onClick = { onCancel() }) {
                Text("OK")
            }
        },
        title = @Composable {
            Text("Error")
        },
        text = @Composable {
            Text("Please enter a valid zip code")
        }
    )
}



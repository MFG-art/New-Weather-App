package com.example.myapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ForecastScreen
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

const val apiKey = "2bfdcae4c4869dd4b000b9a614fbd601"
const val zip = "55075"
// This is my assignment1 branch
@HiltAndroidApp
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "MainScreen"){
                composable("MainScreen") { ScaffoldWithTopBar(navController) }
                composable("ForecastScreen"){ ForecastScreen(navController) }
            }
        }
    }
}

object RetrofitHelper {
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.openweathermap.org/").build()
    }
}

//interface CurrentWeatherDataApi {
//    @GET("/data/2.5/weather?zip={$zip}&appid={$apiKey}")
//
//
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBar(navController: NavController) {
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
                    modifier = Modifier.padding(0.dp,20.dp,0.dp,0.dp)
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
                Button(onClick = { navController.navigate("ForecastScreen")
                } ) {
                    Text(text = "Forecast")
                }
            }
        })
}
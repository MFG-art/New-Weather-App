package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// create 16 unique forecast items
private val forecastItems = listOf(
    // 1
    DayForecast(
        1688965200L, // 7/10
        1688985360L, // 5:36 AM
        1689040800L, // 9:00 PM
        ForecastTemp(83.2F,92.0F, 57.8F),
        1020.23F,
        50,
        ),
    // 2
    DayForecast(
        1689051600L, // 7/11
        1689071820L, // 5:37 AM
        1689127140L, // 8:59 PM
        ForecastTemp(84.2F,92.0F, 57.8F),
        1019.23F,
        52,
        ),
    // 3
    DayForecast(
        1689138000L, // 7/12
        1689158220L, // 5:37 AM
        1689213480L, // 8:58 PM
        ForecastTemp(85.2F,92.0F, 57.8F),
        1019.06F,
        56,
        ),
    // 4
    DayForecast(
        1689224400L, // 7/13
        1689244680L, // 5:38 AM 
        1689299820L, // 8:57 PM
        ForecastTemp(86.2F,92.0F, 57.8F),
        1018.26F,
        60,
        ),
    // 5
    DayForecast(
        1689310800L, // 7/14
        1689331140L, // 5:39 AM
        1689386160L, // 8:56 PM
        ForecastTemp(81.2F,92.0F, 57.8F),
        1020.30F,
        58,
        ),
    // 6
    DayForecast(
        1689397200L, // 7/15
        1689417600L, // 5:40 AM
        1689472500L, // 8:55 PM
        ForecastTemp(80.2F,92.0F, 57.8F),
        1020.27F,
        51,
        ),
    // 7
    DayForecast(
        1689483600L, // 7/16
        1689504060L, // 5:41 AM
        1689558780L, // 8:53 PM
        ForecastTemp(78.2F,92.0F, 57.8F),
        1021.30F,
        50,
        ),
    // 8
    DayForecast(
        1689570000L, // 7/17
        1689590580L, // 5:43 AM
        1689645120L, // 8:52 PM
        ForecastTemp(79.5F,92.0F, 57.8F),
        1017.15F,
        56,
        ),
    // 9
    DayForecast(
        1689656400L, // 7/18
        1689677040L, // 5:44 AM
        1689731460L, // 8:51 PM
        ForecastTemp(80.8F,92.0F, 57.8F),
        1017.83F,
        57,
        ),
    // 10
    DayForecast(
        1689742800L, // 7/19
        1689763500L, // 5:45 AM
        1689817740L, // 8:49 PM
        ForecastTemp(83.5F,92.0F, 57.8F),
        1018.20F,
        59,
        ),
    // 11
    DayForecast(
        1689829200L, // 7/20
        1689849960L, // 5:46 AM
        1689904080L, // 8:48 PM
        ForecastTemp(86.2F,92.0F, 57.8F),
        1018.28F,
        55,
        ),
    // 12
    DayForecast(
        1689915600L, // 7/21
        1689936420L, // 5:47 AM
        1689990420L, // 8:47 PM
        ForecastTemp(75.8F,92.0F, 57.8F),
        1018.90F,
        53,
        ),
    // 13
    DayForecast(
        1690002000L, // 7/22
        1690022940L, // 5:49 AM
        1690076760L, // 8:46 PM
        ForecastTemp(76.2F,92.0F, 57.8F),
        1019.01F,
        51,
        ),
    // 14
    DayForecast(
        1688965200L, // 7/23
        1690109400L, // 5:50 AM
        1690163100L, // 8:45 PM
        ForecastTemp(74.9F,92.0F, 57.8F),
        1019.23F,
        52,
        ),
    // 15
    DayForecast(
        1690174800L, // 7/24
        1690195860L, // 5:51 AM
        1690249380L, // 8:43 PM
        ForecastTemp(70.5F,92.0F, 57.8F),
        1020.01F,
        49,
        ),
    // 16
    DayForecast(
        1690261200L, // 7/25
        1690282320L, // 5:52 AM
        1690335720L, // 8:42 PM
        ForecastTemp(70.9F,92.0F, 57.8F),
        1020.06F,
        53,
        ),
        



)


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ForecastScreen(navController: NavController) {
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
                    DataItemList(navController,forecastItems)
                }
            })
    }

@Composable
fun DataItemList(navController: NavController, forecastItems: List<DayForecast>) {
    LazyColumn{
        items(forecastItems) {
            forecastItem ->
            ForecastItemView(dayForecast = forecastItem)
        }
    }
}



@Composable
fun ForecastItemView(dayForecast: DayForecast) {
    /* Create the view for the data item here. */
    Row(
        modifier = Modifier
            .height(100.dp).background(Color.Gray)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.sun),
            contentDescription = stringResource(id = R.string.sun_string)
        )

    }

}
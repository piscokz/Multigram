package com.piscokz.Pengolah_rumus_compose.Home

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.Programs.customSwitchColor
import com.piscokz.Pengolah_rumus_compose.Programs.switchIconButtonColors
import com.piscokz.Pengolah_rumus_compose.Programs.switchColorText
import com.piscokz.Pengolah_rumus_compose.R
import com.piscokz.Pengolah_rumus_compose.Screen
import com.piscokz.Pengolah_rumus_compose.ui.theme.ColorTextLightMode
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightBlue
import com.piscokz.Pengolah_rumus_compose.ui.theme.multigramTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController
) {
    multigramTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Row {
                            Text(
                                "Multi",
                                color = customSwitchColor(
                                    lighMode = Color.Black,
                                    darkMode = Color.LightGray
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                "Gram",
                                color = customSwitchColor(
                                    lighMode = ColorTextLightMode,
                                    darkMode = LightBlue
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    },
                )

            }

        ) { paddingValues ->
            HomeBody(
                paddingValues = paddingValues,
                navController = navController
            )
        }
    }
}

@Composable
fun listProgram(
    navController: NavController,
    namaProgram: String,
    jenisProgram: String,
    screen_route: String,
    id_resource: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(25.dp),
        shadowElevation = 5.dp,
        color = LightBlue,
        modifier = Modifier.padding(bottom = 20.dp),
    ) {
        OutlinedIconButton(
            shape = RoundedCornerShape(25.dp),
            onClick = {navController.navigate(screen_route)},
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(160.dp)
                .padding(top = if (isSystemInDarkTheme()) 5.dp else 0.dp)
                ,
            colors = switchIconButtonColors(),
            border = BorderStroke(1.dp, Color.Transparent)
        ) {
            Row(
                modifier = modifier
                    .widthIn(min = 450.dp, max = 700.dp)
                    .height(150.dp)
                    .background(if (isSystemInDarkTheme()) Color.Black else LightBlue)
                    .padding(start = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = jenisProgram,
                        color = switchColorText(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Serif
                    )
                    val text = namaProgram.replaceFirstChar { it.titlecase() }
                    Text(
                        text =
                        if (text.length >= 20) {
                            text.substringBeforeLast(" ") + "\n" + text.substringAfterLast(" ")

                        } else text
                        ,
                        fontSize = 23.sp,
                        color = switchColorText(),
                        style = MaterialTheme.typography.headlineSmall,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium
                    )
                }
                Column() {
                    Image(painter = painterResource(id = id_resource), contentDescription = null)
                }
            }
        }

    }
}

@Composable
fun HomeBody(
    paddingValues: PaddingValues,
    navController: NavController
) {
    val listProgram: Array<String> = stringArrayResource(R.array.listProgram)
    val listjenisProgram: Array<String> = stringArrayResource(R.array.jenisProgram)
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(top = 10.dp)
            .fillMaxSize(),
    ) {
        item {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                listProgram(
                    navController = navController,
                    namaProgram = listProgram[0],
                    jenisProgram = listjenisProgram[1],
                    screen_route = Screen.Kb.route,
                    id_resource = R.drawable.byte_logo,
                    modifier = Modifier.padding(end = 15.dp)
                )
            }
        }
        item {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
//                Text(
//                    color = switchColorText(),
//                    text = myStringArray[1],
//                    style = MaterialTheme.typography.headlineMedium,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(start = 5.dp, bottom = 15.dp)
//                )
                listProgram(
                    navController = navController,
                    namaProgram = listProgram[1],
                    jenisProgram = listjenisProgram[1],
                    screen_route = Screen.Km.route,
                    id_resource = R.drawable.meter_logo,
                    modifier = Modifier.padding(end = 15.dp)
                )
                listProgram(
                    navController = navController,
                    namaProgram = listProgram[2],
                    jenisProgram = listjenisProgram[0],
                    screen_route = Screen.Lpp.route,
                    id_resource = R.drawable.lpp_logo,
                    modifier = Modifier.padding(end = 15.dp)
                )
                listProgram(
                    navController = navController,
                    namaProgram = listProgram[3],
                    jenisProgram = listjenisProgram[0],
                    screen_route = Screen.Kpp.route,
                    id_resource = R.drawable.rectangle_shape,
                    modifier = Modifier.padding(end = 15.dp)
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun prev() {
    Home(navController = NavController(context = LocalContext.current))
}
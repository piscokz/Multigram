package com.piscokz.Pengolah_rumus_compose.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.Programs.listProgram
import com.piscokz.Pengolah_rumus_compose.Programs.listRumus
import com.piscokz.Pengolah_rumus_compose.Programs.navProgram
import com.piscokz.Pengolah_rumus_compose.Programs.switchButtonColors
import com.piscokz.Pengolah_rumus_compose.Programs.switchColor
import com.piscokz.Pengolah_rumus_compose.Programs.tipeProgram
import com.piscokz.Pengolah_rumus_compose.R
import com.piscokz.Pengolah_rumus_compose.Screen
import com.piscokz.Pengolah_rumus_compose.ui.theme.PengolahRumusComposeTheme
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController
) {
    PengolahRumusComposeTheme {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                "MultiGram",
                                color = switchColor(),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.ExtraBold
                            )
                        },
                    )

                }

            ) { paddingValues ->
                HomeBody2(paddingValues = paddingValues, navController = navController)
            }
        }
    }

@Composable
fun listProgram(
    navController: NavController,
    namaProgram: String,
    jenisProgram: String,
    screen_route: String,
    id_resource : Int
) {
    Surface(
        shape = RoundedCornerShape(15.dp),
    ) {
        Row(
            modifier = Modifier
                .clickable { navController.navigate(screen_route) }
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 10.dp)
                .background(MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = jenisProgram,
                    color = switchColor(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = namaProgram.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    color = switchColor(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium
                    )
            }
            Column(
                Modifier.padding(end = 10.dp)
            ) {
                Image(painter = painterResource(id = id_resource), contentDescription = null)
            }
        }
    }
}

@Composable
fun HomeBody2(
    paddingValues: PaddingValues,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 10.dp, vertical = 15.dp),
    ) {
        item {
            Column() {
                Text(
                    modifier = Modifier.padding(start = 5.dp, bottom = 15.dp),
                    color = switchColor(),
                    text = "Rekomendasi ",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
            listProgram(
                navController = navController,
                namaProgram = "keliling persegi panjang",
                jenisProgram = "rumus",
                screen_route = "kpp_screen",
                id_resource = R.drawable.rectangle_shape
            )

            Column(
                modifier = Modifier
                    .padding(start = 10.dp, bottom = 15.dp, top = 20.dp)
            ) {
                Text(
                    color = switchColor(),
                    text = "Lainnya ",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
            listProgram(
                navController = navController,
                namaProgram = "luas persegi panjang",
                jenisProgram = "rumus",
                screen_route = "lp_screen",
                id_resource = R.drawable.rectangle_shape
            )
        }
    }
}

@Composable
fun HomeBody(
    paddingValues: PaddingValues,
    navController: NavController
) {
    val lebarTombol = 100.dp
    val rekomendasi = listRumus[0]

    LazyColumn {
        item {
            Column(
                modifier = Modifier
                    .padding(paddingValues = paddingValues)
                    .padding(start = 10.dp, bottom = 5.dp, top = 20.dp)

            ) {
                Text(
                    color = switchColor(),
                    text = "Rekomendasi ",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )
            }

            Surface(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                shape = RoundedCornerShape(15.dp)

            ) {

                Row(
                    modifier = Modifier
                        .padding(vertical = 50.dp, horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Column(modifier = Modifier.weight(0.5f)) {
                        Text(
                            color = switchColor(),
                            text = tipeProgram(rekomendasi),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium

                        )
                        Text(
                            color = switchColor(),
                            text = rekomendasi,
                            style = MaterialTheme.typography.headlineMedium,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    ElevatedButton(
                        shape = RoundedCornerShape(35.dp),
                        colors = switchButtonColors(),
                        onClick = {
                            navProgram(navController, rekomendasi)
                        },
                        modifier = Modifier
                            .width(lebarTombol)
                            .padding(start = 5.dp)
                    ) {
                        Text(
                            color = switchColor(),
                            text = "Lihat",
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, bottom = 5.dp, top = 20.dp)

            ) {
                Text(
                    color = switchColor(),
                    text = "Lainnya ",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        items(listProgram.size) {
            Row(
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 10.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .fillMaxWidth()
                            .requiredHeight(100.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(0.5f),
                        ) {
                            Text(
                                color = switchColor(),
                                text = tipeProgram(listProgram[it]),
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                color = switchColor(),

                                text = listProgram[it],
                                style = MaterialTheme.typography.headlineSmall,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        ElevatedButton(
                            shape = RoundedCornerShape(35.dp),
                            colors = switchButtonColors(),
                            modifier = Modifier
                                .width(lebarTombol),
                            onClick = {
                                navProgram(navController, listProgram[it])
                            },
                        ) {
                            Text(
                                text = "Lihat",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

    }
}

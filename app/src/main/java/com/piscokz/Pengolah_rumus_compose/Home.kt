package com.piscokz.Pengolah_rumus_compose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.Programs.listProgram
import com.piscokz.Pengolah_rumus_compose.Programs.listRumus
import com.piscokz.Pengolah_rumus_compose.Programs.navProgram
import com.piscokz.Pengolah_rumus_compose.Programs.tipeProgram
import com.piscokz.Pengolah_rumus_compose.ui.theme.PengolahRumusComposeTheme

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController
) {

    PengolahRumusComposeTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                "MultiGram",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.ExtraBold

                            )
                        },
                    )

                }

            ) { paddingValues ->

                val lebarTombol = 100.dp
                val rekomendasi = listRumus[0]
                Column() {
                    Column(
                        modifier = Modifier
                            .padding(paddingValues = paddingValues)
                            .padding(start = 10.dp, bottom = 5.dp, top = 20.dp)

                    ) {
                        Text(
                            text = "Rekomendasi ",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Surface(
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 10.dp)
                            .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
                    ) {

                        Row(
                            modifier = Modifier.padding(vertical = 50.dp, horizontal = 15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(0.5f)) {
                                Text(
                                    text = tipeProgram(rekomendasi),
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Text(
                                    text = rekomendasi,
                                    style = MaterialTheme.typography.headlineSmall,
                                )
                            }
                            ElevatedButton(
                                onClick = {
                                    navProgram(navController, rekomendasi)
                                },
                                modifier = Modifier
                                    .width(lebarTombol)
                                    .padding(start = 5.dp)
                            ) {
                                Text("Lihat")
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp, bottom = 5.dp, top = 20.dp)

                    ) {
                        Text(
                            text = "Lainnya ",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    LazyRow() {
                        items(listProgram.size) {
                            Row(
                                modifier = Modifier
                                    .padding(vertical = 15.dp, horizontal = 2.dp)
                                    .padding(start = 8.dp)
                                    .border(
                                        1.dp,
                                        Color.Black,
                                        shape = RoundedCornerShape(15.dp)
                                    )

                            ) {

                                Column(
                                    modifier = Modifier
                                        .padding(horizontal = 15.dp)
                                        .requiredWidth(150.dp)
                                        .requiredHeight(120.dp),
                                    verticalArrangement = Arrangement.SpaceAround,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Column(
                                    ) {
                                        Text(
                                            text = tipeProgram(listProgram[it]),
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                        Text(
                                            text = listProgram[it],
                                            style = MaterialTheme.typography.headlineSmall,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1
                                        )
                                    }
                                    ElevatedButton(
                                        modifier = Modifier.width(lebarTombol),
                                        onClick = {
                                            navProgram(navController, listProgram[it])
                                        },
                                    ) {
                                        Text(
                                            text = "Lihat",
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

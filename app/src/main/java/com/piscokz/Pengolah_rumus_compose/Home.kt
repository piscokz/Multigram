package com.piscokz.Pengolah_rumus_compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.Programs.listProgram
import com.piscokz.Pengolah_rumus_compose.Programs.listRumusPersamaanDuaVariabel
import com.piscokz.Pengolah_rumus_compose.Programs.navProgram
import com.piscokz.Pengolah_rumus_compose.Programs.tipeProgram
import com.piscokz.Pengolah_rumus_compose.ui.theme.PengolahRumusComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController
) {

    PengolahRumusComposeTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
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
                                style = MaterialTheme.typography.headlineLarge

                            )
                        },
//                        actions = {
//                            IconButton(
//                                onClick = {
//
//                                }
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Sharp.MoreVert,
//                                    tint = Color.Black,
//                                    contentDescription = "More settings",
//                                )
//                            }
//                        },
                    )

                }

            ) { paddingValues ->
                var rekomendasi = listProgram[listRumusPersamaanDuaVariabel()]
                Column {
                    Column(
                        modifier = Modifier
                            .padding(paddingValues = paddingValues)
                            .padding(start = 10.dp, bottom = 5.dp, top = 20.dp)

                    ) {
                        Text(
                            text = "Rekomendasi :",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                    Surface(
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 10.dp)
                            .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
                    ) {

                        Row(
                            modifier = Modifier.padding(vertical = 20.dp, horizontal = 15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(0.5f)) {
                                Text(
                                    text = "Rumus",
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Text(
                                    text = rekomendasi,
                                    style = MaterialTheme.typography.headlineSmall,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                            ElevatedButton(
                                onClick = {
                                    navProgram(navController, rekomendasi)
                                },
                                modifier = Modifier
                                    .weight(0.3f),
                            ) {
                                Text("Show")
                            }
                        }
                    }
//                LazyColumn(
//                    modifier = Modifier
//                        .padding(paddingValues)
//                        .padding(vertical = 10.dp, horizontal = 10.dp)
//                        .clip(RoundedCornerShape(15.dp)),
//                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp, bottom = 5.dp, top = 20.dp)

                    ) {
                        Text(
                            text = "Lainnya :",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }

                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(150.dp)
                    ) {
                        var i = 0
                            items(listProgram.size) {
                                Box(
                                    modifier = Modifier
                                        .padding(vertical = 15.dp, horizontal = 10.dp)
                                        .border(
                                            1.dp,
                                            Color.Black,
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .requiredHeight(120.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .padding(vertical = 15.dp, horizontal = 15.dp),
                                    ) {
                                        Column(
                                            modifier = Modifier.weight(0.6f)
                                        ) {
                                            Text(
                                                text = tipeProgram(it),
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                            Text(
                                                text = listProgram[it],
                                                style = MaterialTheme.typography.headlineSmall,
                                                overflow = TextOverflow.Ellipsis,
                                            )
                                        }
                                        ElevatedButton(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .weight(0.4f),
                                            onClick = {
                                                navProgram(navController, listProgram[it])
                                            },
                                        ) {
                                            Text(
                                                text = "Show",
                                            )
                                        }
                                    }
                                    i++
                                }
                            }
                    }

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Prev() {
    PengolahRumusComposeTheme {
    }
}


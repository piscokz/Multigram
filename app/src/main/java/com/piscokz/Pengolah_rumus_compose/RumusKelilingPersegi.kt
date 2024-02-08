package com.piscokz.Pengolah_rumus_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.ui.theme.PengolahRumusComposeTheme

//navigationIcon = {
//    IconButton(onClick = { /*TODO*/ }) {
//        Icon(
//            imageVector = Icons.Filled.ArrowBack,
//            contentDescription = "Back"
//        )
//    }
//},

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RumusKelilingPersegi(
    navController: NavController
) {
    PengolahRumusComposeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            TopAppBar(
                title = {
                    Text(
                        "rumus keliling persegi",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
            )
            Column(
                modifier = Modifier.padding(horizontal = 15.dp),
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Rumus keliling persegi",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.persegi),
//                        contentDescription = stringResource(id = R.string.deskripsi_persegi),
//                        modifier = Modifier
//                            .size(200.dp),
//                    )
                }

                Column(
                    Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = stringResource(id = R.string.sub_judul_deskripsi),
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .padding(bottom = 10.dp),
                    )
                }

                Column(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.isi_deskripsi),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(bottom = 20.dp),
                    )
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.sub_judul_sifat),
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .padding(bottom = 10.dp),
                    )
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.isi_sifat_1),
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    Text(
                        text = stringResource(R.string.isi_sifat_2),
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    Text(
                        text = stringResource(R.string.isi_sifat_3),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(bottom = 20.dp),
                    )
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.sub_judul_rumus),
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .padding(bottom = 10.dp),
                    )
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.isi_rumus),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(bottom = 20.dp),
                    )

                }
            }

        }
    }
}




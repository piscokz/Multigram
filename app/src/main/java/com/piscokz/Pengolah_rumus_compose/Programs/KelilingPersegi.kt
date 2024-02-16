package com.piscokz.Pengolah_rumus_compose.Programs

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
fun PersamaanDuaVariabel(
    navController: NavController
) {
    PengolahRumusComposeTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                listRumus[0],
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.headlineLarge

                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    )

                }

            ) { paddingValues ->

                val lebarTexfield = 120
                val marginBawah = 20
                val marginBawahSesi = 40

                var panjang by remember { mutableStateOf("") }
                var lebar by remember { mutableStateOf("") }

                var display1 = "Kp = 2 * ( panjang + lebar )"
                var display2 by remember {
                    mutableStateOf("")
                }

                var tekanTombolHitung by remember {
                    mutableStateOf(false)
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {

                    item {


                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(marginBawah.dp)
                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                        ) {

                            Text(
                                text =
                                when {
                                    display2.isNotEmpty() -> display2
                                    panjang.isNotEmpty() && lebar.isEmpty() -> "Kp = 2 * ( $panjang + lebar )"
                                    lebar.isNotEmpty() && panjang.isEmpty() -> "Kp = 2 * ( panjang + $lebar )"
                                    panjang.isNotEmpty() && lebar.isNotEmpty() -> "Kp = 2 * ( $panjang + $lebar )"
                                    else -> {
                                        display1
                                    }
                                },
                                fontFamily = FontFamily.Serif,
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .border(1.dp, Color.Black)
                                    .padding(15.dp),
                                textAlign = TextAlign.Center,
                                fontStyle = FontStyle.Italic,
                            )

                        }

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(marginBawah.dp)
                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .fillMaxWidth(1f),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {

                                OutlinedTextField(
                                    value = panjang,
                                    onValueChange = { panjang = it },
                                    label = {
                                        Text(
                                            text = "panjang",
                                            fontFamily = FontFamily.Serif,
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Center
                                        )
                                    },
                                    textStyle = LocalTextStyle.current.copy(
                                        textAlign = TextAlign.Right
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    singleLine = true,
                                    modifier = Modifier
                                        .width(lebarTexfield.dp),

                                    suffix = {
                                        Text(
                                            text = " cm",
                                            fontFamily = FontFamily.Monospace,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                    },
                                    supportingText = {
                                        if (panjang.isEmpty()) Text(text = "masukkan angka")
                                    },
                                    isError = panjang.isEmpty() && tekanTombolHitung
                                )

                                OutlinedTextField(
                                    value = lebar,
                                    onValueChange = { lebar = it },
                                    label = {
                                        Text(
                                            text = "lebar",
                                            fontFamily = FontFamily.Serif,
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Center
                                        )
                                    },
                                    textStyle = LocalTextStyle.current.copy(
                                        textAlign = TextAlign.Right
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number,
                                    ),
                                    singleLine = true,
                                    modifier = Modifier
                                        .width(lebarTexfield.dp)
                                        .pointerInput(key1 = true) {
                                            detectTapGestures(
                                                onLongPress = {

                                                }
                                            )
                                        },
                                    suffix = {
                                        Text(
                                            text = " cm",
                                            fontFamily = FontFamily.Monospace,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                    },
                                    supportingText = {
                                        if (lebar.isEmpty()) Text(text = "masukkan angka")
                                    },
                                    isError = lebar.isEmpty() && tekanTombolHitung
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(marginBawah.dp)
                            )

                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(marginBawah.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .fillMaxWidth(1f),
                                horizontalArrangement = Arrangement.End
                            ) {
                                ElevatedButton(
                                    onClick = {
                                        panjang = ""
                                        lebar = ""
                                        display2 = ""


                                    },
                                ) {
                                    Text(text = "Reset")
                                }

                                ElevatedButton(
                                    onClick = {
                                        tekanTombolHitung = panjang.isEmpty() || lebar.isEmpty()
                                        if (panjang.isNotEmpty() && lebar.isNotEmpty()) {
                                            display2 = kpp(panjang, lebar)
                                        }
                                    },
                                    modifier = Modifier.padding(start = 10.dp),
                                ) {
                                    Text(
                                        text = "Hitung",
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(marginBawahSesi.dp)
                            )
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .border(1.dp, Color.Black)
                            )
                        }
                    }
                }
            }
        }
    }
}

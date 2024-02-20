package com.piscokz.Pengolah_rumus_compose.Programs

import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.ui.theme.PengolahRumusComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ukuranPanjang(
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
                                listKonversi[0],
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
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    )

                }

            ) { paddingValues ->

                var konversi by remember { mutableStateOf("") }

                var display2 by remember {
                    mutableStateOf("")
                }

                var tekanTombolHitung by remember {
                    mutableStateOf(false)
                }

                var expanded1 by remember { mutableStateOf(false) }
                var expandedKonversi by remember { mutableStateOf(false) }

                var ukuranInput1 by remember { mutableStateOf("cm") }
                var ukuranInputKonversi by remember { mutableStateOf("cm") }

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
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                        ) {
                            Row {
                                OutlinedTextField(
                                    value = konversi,
                                    onValueChange = { konversi = it },
                                    label = {
                                        Text(
                                            color = switchColor(),
                                            text = "konversi",
                                            style = MaterialTheme.typography.titleLarge,
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
                                        .fillMaxWidth(0.4f),
                                    suffix = {
                                        Text(
                                            color = switchColor(),
                                            text = " $ukuranInput1",
                                            fontFamily = FontFamily.Monospace,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                    },
                                    supportingText = {
                                        if (konversi.isEmpty()) Text(
                                            text = "masukkan angka",
                                            color = switchColor()
                                        )
                                    },
                                    isError = konversi.isEmpty() && tekanTombolHitung
                                )
                                IconButton(onClick = { expanded1 = true }) {
                                    Icon(
                                        Icons.Default.ArrowDropDown,
                                        contentDescription = "Localized description"
                                    )
                                }
                                DropdownMenu(
                                    expanded = expanded1,
                                    onDismissRequest = { expanded1 = false }
                                ) {
                                    for (i in listUkuranPanjang) {
                                        DropdownMenuItem(
                                            text = { Text(text = i) },
                                            onClick = { ukuranInput1 = i })
                                    }
                                }
                                Column(
                                    modifier = Modifier.padding(top = 5.dp)
                                ) {
                                    Text(
                                        color = switchColor(),
                                        text = displayUkuranPanjang(
                                            display2,
                                            konversi,
                                            ukuranInput1
                                        ),
                                        fontFamily = FontFamily.Serif,
                                        style = MaterialTheme.typography.headlineMedium,
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .border(
                                                1.dp,
                                                switchColor(),
                                                shape = RoundedCornerShape(5.dp)
                                            )
                                            .padding(vertical = 15.dp),
                                        textAlign = TextAlign.Center,
                                        fontStyle = FontStyle.Italic,
                                    )
                                }
                            }
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
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start

                            ) {
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
                                IconButton(onClick = { expandedKonversi = true }) {
                                    Icon(
                                        Icons.Default.ArrowDropDown,
                                        contentDescription = "Localized description"
                                    )
                                }
                                DropdownMenu(
                                    expanded = expandedKonversi,
                                    onDismissRequest = { expandedKonversi = false }
                                ) {
                                    for (i in listUkuranPanjang) {
                                        DropdownMenuItem(
                                            text = { Text(text = i) },
                                            onClick = { ukuranInputKonversi = i })
                                    }
                                }
                                ElevatedButton(
                                    shape = RoundedCornerShape(35.dp),
                                    colors = switchButtonColors(),
                                    onClick = {
                                        if (cekInput(konversi)) {
                                            tekanTombolHitung = konversi.isEmpty()
                                            if (konversi.isNotEmpty()) {
                                                konversi = konversiUkuranPanjang(
                                                    ukuranInput1,
                                                    ukuranInputKonversi,
                                                    konversi
                                                )
                                                display2 = "$konversi $ukuranInputKonversi"
                                                konversi = ""
                                            }
                                        }

                                    },
                                    modifier = Modifier.padding(end = 10.dp),
                                ) {
                                    Text(
                                        text = "konversi $ukuranInputKonversi",
                                        textAlign = TextAlign.Center
                                    )
                                }
                                ElevatedButton(
                                    shape = RoundedCornerShape(35.dp),
                                    colors = switchButtonColors(),
                                    onClick = {
                                        konversi = ""
                                        display2 = ""
                                        tekanTombolHitung = false
                                    },
                                ) {
                                    Text(text = "Reset")
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
                                    .border(1.dp, switchColor())
                            )
                        }
                    }
                }
            }
        }
    }
}


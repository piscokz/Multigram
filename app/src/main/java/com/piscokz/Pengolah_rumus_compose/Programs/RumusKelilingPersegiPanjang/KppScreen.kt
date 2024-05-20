package com.piscokz.Pengolah_rumus_compose.Programs.RumusKelilingPersegiPanjang

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.AppViewModelProvider
import com.piscokz.Pengolah_rumus_compose.Programs.cekInput
import com.piscokz.Pengolah_rumus_compose.Programs.listRumus
import com.piscokz.Pengolah_rumus_compose.Programs.switchButtonColors
import com.piscokz.Pengolah_rumus_compose.Programs.switchColor
import com.piscokz.Pengolah_rumus_compose.ui.theme.PengolahRumusComposeTheme

val listUkuranPanjang: List<String> = listOf("mm", "cm", "dm", "m", "dam", "hm", "km")

const val lebarTexfield = 120
const val marginBawah = 20
const val marginBawahSesi = 40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Kpp(
    navController: NavController,
    kppViewModel: KppViewModel = viewModel(factory = AppViewModelProvider.Factory)
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
                                color = switchColor(),
                                text = listRumus[0],
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
                KppBody(paddingValues = paddingValues, kppViewModel = kppViewModel)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun KppBody(
    paddingValues: PaddingValues,
    kppViewModel: KppViewModel
) {

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
                    color = switchColor(),
                    text = kppViewModel.displayKpp(),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Thin,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .border(1.dp, switchColor())
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
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Row {
                        OutlinedTextField(
                            value = kppViewModel.inputPanjang,
                            onValueChange = { kppViewModel.inputPanjang = it },
                            label = {
                                Text(
                                    color = switchColor(),
                                    text = "panjang",
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold
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
                                    color = switchColor(),
                                    text = " ${kppViewModel.ukuranInputPanjang}",
                                    fontFamily = FontFamily.Monospace,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            },
                            supportingText = {
                                if (kppViewModel.inputPanjang.isEmpty()) Text(
                                    text = "masukkan angka",
                                    color = switchColor()
                                )
                            },
                            isError = kppViewModel.inputPanjang.isEmpty() && kppViewModel.tekanTombolHitung
                        )
                        IconButton(onClick = { kppViewModel.expandedPanjang = true }) {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Localized description"
                            )
                        }
                        DropdownMenu(
                            expanded = kppViewModel.expandedPanjang,
                            onDismissRequest = { kppViewModel.expandedPanjang = false }
                        ) {
                            for (i in listUkuranPanjang) {
                                DropdownMenuItem(
                                    text = { Text(text = i) },
                                    onClick = { kppViewModel.ukuranInputPanjang = i })
                            }
                        }
                    }
                    Row {
                        DropdownMenu(
                            expanded = kppViewModel.expandedLebar,
                            onDismissRequest = { kppViewModel.expandedLebar = false }
                        ) {
                            for (i in listUkuranPanjang) {
                                DropdownMenuItem(
                                    text = { Text(text = i) },
                                    onClick = { kppViewModel.ukuranInputLebar = i })
                            }
                        }
                        IconButton(onClick = { kppViewModel.expandedLebar = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Localized description")
                        }
                        OutlinedTextField(
                            value = kppViewModel.inputLebar,
                            onValueChange = { kppViewModel.inputLebar = it },
                            label = {
                                Text(
                                    color = switchColor(),
                                    text = "lebar",
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold

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
                                },
                            suffix = {
                                Text(
                                    color = switchColor(),
                                    text = " ${kppViewModel.ukuranInputLebar}",
                                    fontFamily = FontFamily.Monospace,
                                    style = MaterialTheme.typography.titleMedium,
                                )
                            },
                            supportingText = {
                                if (kppViewModel.inputLebar.isEmpty()) Text(
                                    text = "masukkan angka",
                                    color = switchColor()
                                )
                            },
                            isError = kppViewModel.inputLebar.isEmpty() && kppViewModel.tekanTombolHitung
                        )
                    }
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
                    IconButton(onClick = { kppViewModel.expandedHitung = true }) {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Localized description"
                        )
                    }
                    DropdownMenu(
                        expanded = kppViewModel.expandedHitung,
                        onDismissRequest = { kppViewModel.expandedHitung = false }
                    ) {
                        for (i in listUkuranPanjang) {
                            DropdownMenuItem(
                                text = { Text(text = i) },
                                onClick = { kppViewModel.ukuranInputHitung = i })
                        }
                    }
                    ElevatedButton(
                        shape = RoundedCornerShape(35.dp),
                        colors = switchButtonColors(),
                        onClick = {
                            if (cekInput(kppViewModel.inputPanjang, kppViewModel.inputLebar)) {
                                kppViewModel.tekanTombolHitung =
                                    kppViewModel.inputPanjang.isEmpty() || kppViewModel.inputLebar.isEmpty()

                                if (kppViewModel.inputPanjang.isNotEmpty() && kppViewModel.inputLebar.isNotEmpty()) {
                                    kppViewModel.panjang = kppViewModel.inputPanjang
                                    kppViewModel.lebar = kppViewModel.inputLebar

                                    kppViewModel.panjang = kppViewModel.konversiUkuranKppPanjang()
                                    kppViewModel.lebar = kppViewModel.konversiUkuranKppLebar()

                                    kppViewModel.display = kppViewModel.hitungKpp()

                                } else {
                                    kppViewModel.tekanTombolHitung = true
                                }
                            }
                        },
                        modifier = Modifier.padding(end = 10.dp),
                    ) {
                        Text(
                            color = switchColor(),
                            text = "Hitung & konversikan ${kppViewModel.ukuranInputHitung}",
                            textAlign = TextAlign.Center
                        )
                    }
                    ElevatedButton(
                        shape = RoundedCornerShape(35.dp),
                        colors = switchButtonColors(),
                        onClick = {
                            kppViewModel.inputPanjang = ""
                            kppViewModel.inputLebar = ""
                            kppViewModel.display = ""
                            kppViewModel.tekanTombolHitung = false
                        },
                    ) {
                        Text(
                            color = switchColor(),
                            text = "Reset"
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
                        .border(1.dp, switchColor())
                )
            }
        }
    }
}

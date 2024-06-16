package com.piscokz.Pengolah_rumus_compose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.Programs.RumusLuasPersegiPanjang.LppViewModel
import com.piscokz.Pengolah_rumus_compose.Programs.cekInput
import com.piscokz.Pengolah_rumus_compose.Programs.listRumus
import com.piscokz.Pengolah_rumus_compose.Programs.switchButtonColors
import com.piscokz.Pengolah_rumus_compose.Programs.switchColor

val listUkuranPanjang: List<String> = listOf("mm", "cm", "dm", "m", "dam", "hm", "km")

const val lebarTexfield = 120
const val marginBawah = 20
const val marginBawahSesi = 40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lpp(
    navController: NavController,
    lppViewModel: LppViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
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
                            listRumus[1],
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.headlineLarge

                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
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
            LppBody(paddingValues = paddingValues, lppViewModel = lppViewModel)
        }
    }
}

@Composable
fun LppBody(
    paddingValues: PaddingValues,
    lppViewModel: LppViewModel,
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
                    text = lppViewModel.displayLpp(),
                    fontFamily = FontFamily.Serif,
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
                            value = lppViewModel.inputPanjang,
                            onValueChange = { lppViewModel.inputPanjang = it },
                            label = {
                                Text(
                                    color = switchColor(),
                                    text = "panjang",
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            },
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Center
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            modifier = Modifier
                                .width(lebarTexfield.dp),
                            suffix = {
                                Text(
                                    color = switchColor(),
                                    text = " ${lppViewModel.ukuranInputPanjang}",
                                    fontFamily = FontFamily.Monospace,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.clickable {
                                        lppViewModel.expandedPanjang = true
                                    }
                                )
                            },
                            supportingText = {
                                if (lppViewModel.inputPanjang.isEmpty()) Text(
                                    text = "masukkan angka",
                                    color = switchColor()
                                )
                            },
                            isError = lppViewModel.inputPanjang.isEmpty() && lppViewModel.tekanTombolHitung
                        )
//                        IconButton(onClick = { lppViewModel.expandedPanjang = true }) {
//                            Icon(
//                                Icons.Default.ArrowDropDown,
//                                contentDescription = "Localized description"
//                            )
//                        }
                        DropdownMenu(
                            expanded = lppViewModel.expandedPanjang,
                            onDismissRequest = { lppViewModel.expandedPanjang = false }
                        ) {
                            for (i in listUkuranPanjang) {
                                DropdownMenuItem(
                                    text = { Text(text = i) },
                                    onClick = {
                                        lppViewModel.ukuranInputPanjang = i
                                        lppViewModel.expandedPanjang = false
                                    })
                            }
                        }
                    }
                    Row {
                        DropdownMenu(
                            expanded = lppViewModel.expandedLebar,
                            onDismissRequest = { lppViewModel.expandedLebar = false }
                        ) {
                            for (i in listUkuranPanjang) {
                                DropdownMenuItem(
                                    text = { Text(text = i) },
                                    onClick = {
                                        lppViewModel.ukuranInputLebar = i
                                        lppViewModel.expandedLebar = false
                                    })
                            }
                        }
//                        IconButton(onClick = { lppViewModel.expandedLebar = true }) {
//                            Icon(
//                                Icons.Default.ArrowDropDown,
//                                contentDescription = "Localized description"
//                            )
//                        }
                        OutlinedTextField(
                            value = lppViewModel.inputLebar,
                            onValueChange = { lppViewModel.inputLebar = it },
                            label = {
                                Text(
                                    color = switchColor(),
                                    text = "lebar",
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            },
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Center
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .width(lebarTexfield.dp),
                            suffix = {
                                Text(
                                    color = switchColor(),
                                    text = " ${lppViewModel.ukuranInputLebar}",
                                    fontFamily = FontFamily.Monospace,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.clickable {
                                        lppViewModel.expandedLebar = true
                                    }
                                )
                            },
                            supportingText = {
                                if (lppViewModel.inputLebar.isEmpty()) Text(
                                    text = "masukkan angka",
                                    color = switchColor()
                                )
                            },
                            isError = lppViewModel.inputLebar.isEmpty() && lppViewModel.tekanTombolHitung
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
                    IconButton(onClick = { lppViewModel.expandedHitung = true }) {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Localized description"
                        )
                    }
                    DropdownMenu(
                        expanded = lppViewModel.expandedHitung,
                        onDismissRequest = { lppViewModel.expandedHitung = false }
                    ) {
                        for (i in listUkuranPanjang) {
                            DropdownMenuItem(
                                text = { Text(text = i) },
                                onClick = { lppViewModel.ukuranInputHitung = i })
                        }
                    }
                    ElevatedButton(
                        shape = RoundedCornerShape(35.dp),
                        colors = switchButtonColors(),
                        onClick = {

                            if (cekInput(lppViewModel.inputPanjang, lppViewModel.inputLebar)) {

                                lppViewModel.tekanTombolHitung =
                                    lppViewModel.inputPanjang.isEmpty() || lppViewModel.inputLebar.isEmpty()

                                if (lppViewModel.inputPanjang.isNotEmpty() && lppViewModel.inputLebar.isNotEmpty()) {
                                    lppViewModel.panjang = lppViewModel.inputPanjang
                                    lppViewModel.lebar = lppViewModel.inputLebar

                                    lppViewModel.panjang = lppViewModel.konversiUkuranLppPanjang()

                                    lppViewModel.lebar = lppViewModel.konversiUkuranLppLebar()

                                    lppViewModel.display = lppViewModel.hitungLpp()

                                }
                            }
                        },
                        modifier = Modifier.padding(end = 10.dp),
                    ) {
                        Text(
                            text = "Hitung & konversikan ${lppViewModel.ukuranInputHitung}",
                            textAlign = TextAlign.Center
                        )
                    }
                    ElevatedButton(
                        shape = RoundedCornerShape(35.dp),
                        colors = switchButtonColors(),
                        onClick = {
                            lppViewModel.inputPanjang = ""
                            lppViewModel.inputLebar = ""
                            lppViewModel.display = ""
                            lppViewModel.tekanTombolHitung = false
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


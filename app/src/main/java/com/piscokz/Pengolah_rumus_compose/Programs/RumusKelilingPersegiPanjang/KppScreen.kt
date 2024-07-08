package com.piscokz.Pengolah_rumus_compose.Programs.RumusKelilingPersegiPanjang

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
import com.piscokz.Pengolah_rumus_compose.Programs.switchColorText
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
                                color = switchColorText(),
                                text = listRumus[0],
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.headlineMedium

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
                KppBody(paddingValues = paddingValues, kppViewModel = kppViewModel)
            }
        }
    }
}

@Composable
fun KppBody(
    paddingValues: PaddingValues,
    kppViewModel: KppViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current
//    val focusRequester = remember { FocusRequester() }
//    var isKeyboardHide by remember { mutableStateOf(true) }

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
                    color = switchColorText(),
                    text = kppViewModel.displayKpp(),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Thin,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .border(1.dp, switchColorText())
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
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        OutlinedTextField(
                            value = kppViewModel.inputPanjang,
                            onValueChange = {
                                kppViewModel.inputPanjang = it
                            },
                            placeholder = {
                                Text(
                                    color = switchColorText(),
                                    text = "panjang",
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Right,
                                    fontStyle = FontStyle.Italic
                                )
                            },
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Right
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done,
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
//                                    isKeyboardHide = true
                                }
                            ),
                            singleLine = true,
                            modifier = Modifier
//                                .width(lebarTexfield.dp)
                                .padding(end = 5.dp)
                                .fillParentMaxWidth(0.4f),
                            suffix = {
                                Text(
                                    color = switchColorText(),
                                    text = " ${kppViewModel.ukuranInputPanjang}",
                                    fontFamily = FontFamily.Monospace,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.clickable {
                                        keyboardController?.hide()
                                        kppViewModel.expandedPanjang = true
                                    }
                                )
                            },
                            supportingText = {
                                if (kppViewModel.inputPanjang.isEmpty()) Text(
                                    text = "masukkan angka",
                                    color = switchColorText()
                                )
                                if(kppViewModel.isError && kppViewModel.inputPanjang.isEmpty()) {
                                    Text(
                                        text = "masukkan angka !",
                                        color = Color.Red
                                    )
                                }
                            },
                            isError = kppViewModel.inputPanjang.isEmpty() && kppViewModel.isError
                        )

                        DropdownMenu(
                            expanded = kppViewModel.expandedPanjang,
                            onDismissRequest = {
                                kppViewModel.expandedPanjang = false
                                keyboardController?.hide()
                            }
                        ) {
                            for (i in listUkuranPanjang) {
                                DropdownMenuItem(
                                    text = { Text(text = i) },
                                    onClick = {
                                        kppViewModel.ukuranInputPanjang = i
                                        kppViewModel.expandedPanjang = false
                                        keyboardController?.hide()
                                    })
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
                                    onClick = {
                                        kppViewModel.ukuranInputLebar = i
                                        kppViewModel.expandedLebar = false
                                    })
                            }
                        }

                        OutlinedTextField(
                            value = kppViewModel.inputLebar,
                            onValueChange = { kppViewModel.inputLebar = it },
                            placeholder = {
                                Text(
                                    color = switchColorText(),
                                    text = "lebar",
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Right,
                                    fontStyle = FontStyle.Italic

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
                                .fillParentMaxWidth(0.4f)
//                                .width(lebarTexfield.dp)
                                .padding(start = 5.dp)
                                .pointerInput(key1 = true) {},
                            suffix = {
                                Text(
                                    color = switchColorText(),
                                    text = " ${kppViewModel.ukuranInputLebar}",
                                    fontFamily = FontFamily.Monospace,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.clickable {
                                        keyboardController?.hide()
                                        kppViewModel.expandedLebar = true
                                    }
                                )
                            },

                            supportingText = {
                                if (kppViewModel.inputLebar.isEmpty()) Text(
                                    text = "masukkan angka",
                                    color = switchColorText()
                                )
                                if(kppViewModel.isError && kppViewModel.inputPanjang.isEmpty()) {
                                    Text(
                                        text = "masukkan angka !",
                                        color = Color.Red
                                    )
                                }
                            },
                            isError = kppViewModel.inputLebar.isEmpty() && kppViewModel.isError
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
                    IconButton(onClick = {
                        keyboardController?.hide()
                        kppViewModel.expandedHitung = true
                    }) {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Localized description"
                        )
                    }
                    DropdownMenu(
                        expanded = kppViewModel.expandedHitung,
                        onDismissRequest = {
                            kppViewModel.expandedHitung = false
                        }
                    ) {
                        for (i in listUkuranPanjang) {
                            DropdownMenuItem(
                                text = { Text(text = i) },
                                onClick = {
                                    kppViewModel.ukuranInputHitung = i
                                    kppViewModel.expandedHitung = false
                                }
                            )
                        }
                    }
                    ElevatedButton(
                        shape = RoundedCornerShape(35.dp),
                        colors = switchButtonColors(),
                        onClick = {
                            if (cekInput(kppViewModel.inputPanjang, kppViewModel.inputLebar)) {
                                kppViewModel.isError =
                                    kppViewModel.inputPanjang.isEmpty() || kppViewModel.inputLebar.isEmpty()

                                if (kppViewModel.inputPanjang.isNotEmpty() && kppViewModel.inputLebar.isNotEmpty()) {
                                    kppViewModel.panjang = kppViewModel.inputPanjang
                                    kppViewModel.lebar = kppViewModel.inputLebar

                                    kppViewModel.panjang = kppViewModel.konversiUkuranKppPanjang()
                                    kppViewModel.lebar = kppViewModel.konversiUkuranKppLebar()

                                    kppViewModel.display = kppViewModel.hitungKpp()

                                } else {
                                    kppViewModel.isError = true
                                }
                            }
                        },
                        modifier = Modifier.padding(end = 10.dp),
                    ) {
                        Text(
                            color = switchColorText(),
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
                            kppViewModel.isError = false
                        },
                    ) {
                        Text(
                            color = switchColorText(),
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
                        .border(1.dp, switchColorText())
                )
            }
        }
    }
}

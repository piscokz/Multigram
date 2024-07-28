package com.piscokz.Pengolah_rumus_compose

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.ui.Programs.RumusLuasPersegiPanjang.LppViewModel
import com.piscokz.Pengolah_rumus_compose.ui.Programs.cekInput
import com.piscokz.Pengolah_rumus_compose.ui.Programs.customSwitchColor
import com.piscokz.Pengolah_rumus_compose.ui.Programs.switchButtonColors
import com.piscokz.Pengolah_rumus_compose.ui.Programs.switchColorText
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightBlue
import com.piscokz.Pengolah_rumus_compose.ui.theme.clearButtonDarkMode

val listUkuranPanjang: List<String> = listOf("mm", "cm", "dm", "m", "dam", "hm", "km")

const val lebarTexfield = 120
const val marginBawah = 20
const val marginBawahSesi = 40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lpp(
    data : Lpp,
    navController: NavController,
    lppViewModel: LppViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
        Scaffold(
//            modifier = Modifier.border((0.5).dp, LightBlue),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            data.judul,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.headlineSmall

                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                LargeFloatingActionButton(
                    onClick = {
                        lppViewModel.inputPanjang = ""
                        lppViewModel.inputLebar = ""
                        lppViewModel.display = ""
                        lppViewModel.isError = false
                    },
                    contentColor = Color.White,
                    containerColor = customSwitchColor(
                        lighMode = Color.Red,
                        darkMode = clearButtonDarkMode
                    )
                ) {
                    Icon(imageVector = Icons.TwoTone.Delete, contentDescription = null)
                }
            }

        ) { paddingValues ->
            LppBody(paddingValues = paddingValues, lppViewModel = lppViewModel)
        }
    }

@Composable
fun LppBody(
    paddingValues: PaddingValues,
    lppViewModel: LppViewModel,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
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
                    text = lppViewModel.displayLpp(),
                    fontFamily = FontFamily.Serif,
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
                            value = lppViewModel.inputPanjang,
                            onValueChange = { lppViewModel.inputPanjang = it },
                            placeholder = {
                                Text(
                                    color = switchColorText(),
                                    text = stringResource(id = R.string.panjang_kpp_lpp),
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Right,
                                    fontStyle = FontStyle.Italic
                                )
                            },
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Right
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            singleLine = true,
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .fillParentMaxWidth(0.4f)
//                                .width(lebarTexfield.dp)
                            ,
                            suffix = {
                                Text(
                                    color = switchColorText(),
                                    text = " ${lppViewModel.ukuranInputPanjang}",
                                    fontFamily = FontFamily.Monospace,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.clickable {
                                        keyboardController?.hide()
                                        lppViewModel.expandedPanjang = true
                                    }
                                )
                            },
                            supportingText = {
                                if (lppViewModel.inputPanjang.isEmpty()) Text(
                                    text = stringResource(id = R.string.placeholderInput),
                                    color = switchColorText()
                                )
                                if (lppViewModel.isError && lppViewModel.inputPanjang.isEmpty()) {
                                    Text(
                                        text = "${stringResource(id = R.string.placeholderInput)} !",
                                        color = Color.Red
                                    )
                                }
                            },
                            isError = lppViewModel.inputPanjang.isEmpty() && lppViewModel.isError
                        )

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
                            offset = DpOffset(x = (40).dp, y = 10.dp),
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
                        OutlinedTextField(
                            value = lppViewModel.inputLebar,
                            onValueChange = { lppViewModel.inputLebar = it },
                            placeholder = {
                                Text(
                                    color = switchColorText(),
                                    text = stringResource(id = R.string.lebar_kpp_lpp),
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
                                .padding(start = 5.dp)
                                .fillParentMaxWidth(0.4f)
//                                .width(lebarTexfield.dp)
                            ,
                            suffix = {
                                Text(
                                    color = switchColorText(),
                                    text = " ${lppViewModel.ukuranInputLebar}",
                                    fontFamily = FontFamily.Monospace,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.clickable {
                                        keyboardController?.hide()
                                        lppViewModel.expandedLebar = true
                                    }
                                )
                            },
                            supportingText = {
                                if (lppViewModel.inputLebar.isEmpty()) Text(
                                    text = stringResource(id = R.string.placeholderInput),
                                    color = switchColorText()
                                )
                                if (lppViewModel.isError && lppViewModel.inputLebar.isEmpty()) Text(
                                    text = "${stringResource(id = R.string.placeholderInput)} !",
                                    color = Color.Red
                                )
                            },
                            isError = lppViewModel.inputLebar.isEmpty() && lppViewModel.isError
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
                    Surface (modifier = Modifier.padding(end = 10.dp)){
                        ElevatedButton(
                            border = BorderStroke(1.dp, LightBlue),
                            colors = switchButtonColors(),
                            onClick = {

                                if (cekInput(lppViewModel.inputPanjang, lppViewModel.inputLebar)) {

                                    lppViewModel.isError =
                                        lppViewModel.inputPanjang.isEmpty() || lppViewModel.inputLebar.isEmpty()

                                    if (lppViewModel.inputPanjang.isNotEmpty() && lppViewModel.inputLebar.isNotEmpty()) {
                                        lppViewModel.panjang = lppViewModel.inputPanjang
                                        lppViewModel.lebar = lppViewModel.inputLebar

                                        lppViewModel.panjang =
                                            lppViewModel.konversiUkuranLppPanjang()

                                        lppViewModel.lebar = lppViewModel.konversiUkuranLppLebar()

                                        lppViewModel.display = lppViewModel.hitungLpp()

                                    }
                                }
                            },
                        ) {
                            Text(
                                color = Color.White,
                                text = stringResource(id = R.string.teksHitung_kpp_lpp),
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                    Button(
                        border = BorderStroke(1.dp, LightBlue),
                        colors = switchButtonColors(),
                        onClick = {
                            keyboardController?.hide()
                            lppViewModel.expandedHitung = true
                        },
                    ) {
                        Row {
                            Text(
                                text = lppViewModel.ukuranInputHitung,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                            if (lppViewModel.expandedHitung) {
                                Icon(
                                    Icons.Filled.KeyboardArrowUp,
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    Icons.Default.KeyboardArrowDown,
                                    contentDescription = null
                                )
                            }
                        }
                    }
//                    Surface (
//                        shape = RoundedCornerShape(405.dp),
//                        color = LightBlue
//                    ){
//
//                    }

                    DropdownMenu(
                        expanded = lppViewModel.expandedHitung,
                        onDismissRequest = { lppViewModel.expandedHitung = false },
                        offset = DpOffset(x = (-15).dp, y = 10.dp),
                    ) {
                        for (i in listUkuranPanjang) {
                            DropdownMenuItem(
                                text = { Text(text = i) },
                                onClick = {
                                    lppViewModel.expandedHitung = false
                                    lppViewModel.ukuranInputHitung = i
                                },
                            )
                        }
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
                        .border(
                            1.dp,
                            customSwitchColor(lighMode = Color.Black, darkMode = Color.LightGray)
                        )
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun prev() {
//    Lpp(lppViewModel = LppViewModel(), navController = NavController(context = LocalContext.current))
}

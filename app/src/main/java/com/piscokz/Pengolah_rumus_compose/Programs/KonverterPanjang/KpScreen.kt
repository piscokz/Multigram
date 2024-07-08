package com.piscokz.Pengolah_rumus_compose.Programs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material.icons.twotone.PlayArrow
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.AppViewModelProvider
import com.piscokz.Pengolah_rumus_compose.Programs.KonverterPanjang.KpViewModel
import com.piscokz.Pengolah_rumus_compose.ui.theme.PengolahRumusComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Kp(
    navController: NavController,
    vm: KpViewModel = viewModel(factory = AppViewModelProvider.Factory)
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
                                text = listKonversi[0],
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
                },
                floatingActionButton = {
                    LargeFloatingActionButton(
                        onClick = {
//                            vm.isInputError = !vm.isInputError
//                            vm.conversion()
                            vm.input = ""
                            vm.outputMm = "0"
                            vm.outputCm = "0"
                            vm.outputDm = "0"
                            vm.outputM = "0"
                            vm.outputDam = "0"
                            vm.outputHm = "0"
                            vm.outputKm = "0"
                        },
                        contentColor = switchColorText(),
                        containerColor = MaterialTheme.colorScheme.surface
                    ) {
//                        Icon(imageVector = Icons.AutoMirrored.TwoTone.ArrowForward, contentDescription = "konversikan")
                        Icon(imageVector = Icons.TwoTone.Delete, contentDescription = "konversikan")
                    }
                }

            ) { paddingValues ->
                LazyColumn {
                    item {
                        KpBodyInput(paddingValues = paddingValues, vm)
                    }
                    item {
                        KpBodyOutput(vm = vm)
                    }
                }

            }
        }
    }
}

@Composable
fun KpBodyInput(
    paddingValues: PaddingValues,
    vm: KpViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(top = 20.dp, start = 5.dp, end = 5.dp)
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                colors = OutlinedTextFieldDefaults.colors(
//                    focusedContainerColor = Color.Yellow
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                textStyle = LocalTextStyle.current.copy(
                    color = switchColorText(),
                    textAlign = TextAlign.Right,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = TextUnit(vm.inputLetterSpacing, TextUnitType.Sp),
                ),
                supportingText = {
                    if (vm.isInputError) {
                        if (vm.input == "") {
                            Text(text = "input minimal 1 angka")
                        }
                    }
                },
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Masukkan angka",
                        letterSpacing = TextUnit(2.5f, TextUnitType.Sp),
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Right,
                        fontStyle = FontStyle.Italic
                    )
                },
                isError = vm.isInputError,
                value = vm.input,
                onValueChange = {
                    vm.input = it
                        .trimStart { it == '0' }
                        .replace("-" ,"")
                        .replace(",", "")
                        .replace(" ","")
                        .replace(".","")
                },
                enabled = true,
                leadingIcon = {
                    IconButton(onClick = {
                        if (vm.inputLetterSpacing == 1.0f) {
                            vm.inputLetterSpacing = 8.0f
                        } else {
                            vm.inputLetterSpacing = 1.0f
                        }
                    }) {
                        Icon(
                            imageVector = Icons.TwoTone.PlayArrow,
                            contentDescription = "letter spacing"
                        )
                    }
                },
                trailingIcon = {
                    Text(
                        text = vm.listMeterCurrent,
                        modifier = Modifier.clickable {
                            keyboardController?.hide()
                            vm.expandedListMeter = true
                        }
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(

                    imeAction = ImeAction.Send,
                    keyboardType = KeyboardType.Number,
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        vm.inputLetterSpacing = 1.0f
                        keyboardController?.hide()
                        vm.conversion()
                    }
                ),
            )

            DropdownMenu(
                offset = DpOffset(x = (-15).dp, y = 0.dp),
                expanded = vm.expandedListMeter,
                onDismissRequest = { vm.expandedListMeter = false })
            {
                for (i in vm.listMeter) {
                    DropdownMenuItem(
                        text = { Text(text = i) },
                        onClick = {
                            vm.listMeterCurrent = i
                            vm.expandedListMeter = false
                            vm.conversion()
                        })
                }
            }
        }
    }
}

@Composable
fun KpBodyOutput(
    modifier: Modifier = Modifier,
    vm: KpViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .border(2.dp, color = switchColorText(), shape = CircleShape.copy(CornerSize(5.dp)))
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(top = 15.dp),
        ) {
            Text(
                text = "Hasil",
                fontFamily = FontFamily.SansSerif,
                color = switchColorText(),
                fontWeight = FontWeight.W300,
                style = MaterialTheme.typography.titleLarge,
                letterSpacing = TextUnit(2.5f, TextUnitType.Sp),
            )
            Row(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    horizontalAlignment = Alignment.Start
                ) {
                    val listOutputListMeter: List<String> = listOf(
                        vm.outputMm,
                        vm.outputCm,
                        vm.outputDm,
                        vm.outputM,
                        vm.outputDam,
                        vm.outputHm,
                        vm.outputKm
                    )
                    for (i in listOutputListMeter) {
//                        33 digit
                        Text(
                            text = if (i.length > 32) {
                                "${
                                    vm.numberSpacing(
                                        vm.isWorthItRoundToLong(
                                            i.subSequence(0, 30).toString()
                                        )
                                    )
                                }+"
                            } else {
                                vm.numberSpacing(vm.isWorthItRoundToLong(vm.notasiIlmiahKonverter(i)))
                            },
                            color = switchColorText(),
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(1.dp)
                                .background(switchColorText())
                        )
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    for (i in vm.listMeter) {
                        Text(
                            text = i,
                            color = switchColorText(),
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(1.dp)
                                .background(switchColorText())
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun prev() {
    KpBodyOutput(vm = KpViewModel())
}
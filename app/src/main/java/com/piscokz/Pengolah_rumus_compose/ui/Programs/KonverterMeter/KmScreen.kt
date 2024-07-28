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
import androidx.compose.material.icons.twotone.KeyboardArrowDown
import androidx.compose.material.icons.twotone.KeyboardArrowUp
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
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
import com.piscokz.Pengolah_rumus_compose.Km
import com.piscokz.Pengolah_rumus_compose.ui.Programs.KonverterMeter.KmViewModel
import com.piscokz.Pengolah_rumus_compose.R
import com.piscokz.Pengolah_rumus_compose.ui.Programs.customSwitchColor
import com.piscokz.Pengolah_rumus_compose.ui.Programs.switchColorText
import com.piscokz.Pengolah_rumus_compose.ui.Programs.switchColorTextWithBackground
import com.piscokz.Pengolah_rumus_compose.ui.theme.clearButtonDarkMode
import com.piscokz.Pengolah_rumus_compose.ui.theme.multigramTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Km(
    data : Km,
    navController: NavController,
    vm: KmViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    multigramTheme {
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
                                text = data.judul,
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
                        contentColor = Color.White,
                        containerColor = customSwitchColor(lighMode = Color.Red, darkMode = clearButtonDarkMode)
                    ) {
//                        Icon(imageVector = Icons.AutoMirrored.TwoTone.ArrowForward, contentDescription = "konversikan")
                        Icon(imageVector = Icons.TwoTone.Delete, contentDescription = "konversikan")
                    }
                }

            ) { paddingValues ->
                LazyColumn {
                    item {
                        KmBodyOutput(
                            vm = vm,
                            paddingValues = paddingValues
                        )
                    }
                    item {
                        KmBodyInput(
//                            paddingValues = paddingValues,
                            vm
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun KmBodyInput(
//    paddingValues: PaddingValues,
    vm: KmViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
//            .padding(paddingValues)
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
//                supportingText = {
//                    if (vm.isInputError) {
//                        if (vm.input == "") {
//                            Text(text = "input minimal 1 angkasa")
//                        }
//                    }
//                },
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.placeholderInput),
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
                        .replace("-", "")
                        .replace(",", "")
                        .replace(" ", "")
                        .replace(".", "")
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
                    Surface(
                        color = switchColorTextWithBackground(),
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        Row(
                            Modifier
                                .background(
                                    customSwitchColor(
                                        lighMode = Color.White,
                                        darkMode = Color.Black
                                    )
                                )
                                .clickable {
                                    keyboardController?.hide()
                                    vm.expandedListMeter = true
                                }
                        ) {
                            Text(
                                text = vm.listMeterCurrent,
                                modifier = Modifier.padding(top = 1.dp)

                            )
                            if (vm.expandedListMeter) Icon(
                                imageVector = Icons.TwoTone.KeyboardArrowUp,
                                contentDescription = null
                            )
                            else Icon(
                                imageVector = Icons.TwoTone.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }

                    }
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
                offset = DpOffset(x = (-15).dp, y = 10.dp),
                expanded = vm.expandedListMeter,
                onDismissRequest = { vm.expandedListMeter = false })
            {
                for (i in vm.listMeter) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = i,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                            )
                        },
                        onClick = {
                            vm.listMeterCurrent = i
                            vm.expandedListMeter = false
                            vm.conversion()
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun KmBodyOutput(
    paddingValues: PaddingValues,
    vm: KmViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
            .padding(top = 15.dp),
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
                text = stringResource(id = R.string.teksHasil),
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
                    modifier = Modifier.fillMaxWidth(0.7f),
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

@Preview(
    showBackground = true
)
@Composable
private fun prev() {
//    Km(
//        vm = KmViewModel(),
//        navController = NavController(LocalContext.current)
//    )
}
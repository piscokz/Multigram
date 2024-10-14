package com.piscokz.Pengolah_rumus_compose.ui.Programs.KonverterGram

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material.icons.twotone.KeyboardArrowDown
import androidx.compose.material.icons.twotone.KeyboardArrowUp
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.AppViewModelProvider
import com.piscokz.Pengolah_rumus_compose.Gram
import com.piscokz.Pengolah_rumus_compose.Kb
import com.piscokz.Pengolah_rumus_compose.Km
import com.piscokz.Pengolah_rumus_compose.Programs.Km
import com.piscokz.Pengolah_rumus_compose.Programs.KmBodyInput
import com.piscokz.Pengolah_rumus_compose.Programs.KmBodyOutput
import com.piscokz.Pengolah_rumus_compose.R
import com.piscokz.Pengolah_rumus_compose.ui.Programs.KonverterByte.KbViewModel
import com.piscokz.Pengolah_rumus_compose.ui.Programs.KonverterMeter.KmViewModel
import com.piscokz.Pengolah_rumus_compose.ui.Programs.customSwitchColor
import com.piscokz.Pengolah_rumus_compose.ui.Programs.switchColorText
import com.piscokz.Pengolah_rumus_compose.ui.Programs.switchColorTextWithBackground
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightBlue
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightButtonColors
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KonverterGramScreen(
    data : Gram,
    navController: NavController,
    vm: KonverterGramViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val focusRequester = remember { FocusRequester() }
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors().copy(
                        containerColor = customSwitchColor(LightButtonColors, Color.Black),
                    ),
                    title = {
                        Text(
                            text = data.judul,
                            color = customSwitchColor(Color.White, Color.LightGray),
                            maxLines = 1,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.W600
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                tint = customSwitchColor(Color.White, Color.LightGray),
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
                        if (vm.input.isNotEmpty()) vm.input = ""
                        else if (vm.input.isEmpty()) {

                            vm.outputMg = "0"
                            vm.outputCg = "0"
                            vm.outputDg = "0"
                            vm.outputG = "0"
                            vm.outputDag = "0"
                            vm.outputHg = "0"
                            vm.outputKg = "0"
                            focusRequester.requestFocus()
                        }
                    },
                    contentColor = Color.White,
                    containerColor = customSwitchColor(
                        lighMode = Color.White,
                        darkMode = Color.DarkGray
                    )
                ) {
                    Icon(
                        tint = customSwitchColor(Color.Black, Color.LightGray),
                        imageVector = Icons.TwoTone.Delete,
                        contentDescription = "konversikan"
                    )
                }
            }

        ) { paddingValues ->
//                LazyColumn {
//                    item {
//                        KmBodyOutput(
//                            vm = vm,
//                            paddingValues = paddingValues
//                        )
//                    }
//                    item {
//                        KmBodyInput(vm, focusRequester)
//                    }
//                }
            Box(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (!isSystemInDarkTheme()) {
                    Image(
                        painter = painterResource(R.drawable.img_background_multigram),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Surface(
//                        shape = RoundedCornerShape(10.dp),
                    color = Color.Red,
                    modifier = Modifier
//                            .padding(top = 50.dp)
//                            .padding(horizontal = 10.dp)
                ) {
                    Column {
                        KgBodyOutput(
                            vm = vm,
                            paddingValues = paddingValues
                        )
                        KgBodyInput(
                            vm = vm,
                            focusRequester = focusRequester
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun KgBodyInput(
    vm: KonverterGramViewModel,
    focusRequester: FocusRequester
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    // Mengatur fokus secara otomatis ketika halaman pertama kali ditampilkan
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            focusRequester.requestFocus()
        }
    }

    Column(
        modifier = Modifier
            .background(customSwitchColor(Color.White, Color.Black))
            .padding(top = 20.dp, start = 5.dp, end = 5.dp),
//            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
//                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = customSwitchColor(Color.White, Color.Black),
                    focusedContainerColor = customSwitchColor(Color.White, Color.Black),
                    focusedBorderColor = LightBlue
                ),
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
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
                    IconButton(
                        onClick = {
//                            if (vm.inputLetterSpacing == 1.0f) {
//                                vm.inputLetterSpacing = 8.0f
//                            } else {
//                                vm.inputLetterSpacing = 1.0f
//                            }
                            vm.conversion()
                        }) {
                        Icon(
                            tint = LightButtonColors,
                            painter = painterResource(R.drawable.spacing),
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
                                    vm.expandedListGram = true
                                }
                        ) {
                            Text(
                                text = vm.listGramCurrent,
                                modifier = Modifier.padding(top = 1.dp)

                            )
                            if (vm.expandedListGram) Icon(
                                tint = LightButtonColors,
                                imageVector = Icons.TwoTone.KeyboardArrowUp,
                                contentDescription = null
                            )
                            else Icon(
                                tint = LightButtonColors,
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
                        focusManager.clearFocus()
                    }
                ),
            )

            DropdownMenu(
                offset = DpOffset(x = (-15).dp, y = 10.dp),
                expanded = vm.expandedListGram,
                onDismissRequest = { vm.expandedListGram = false })
            {
                for (i in vm.listGram) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = i,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                            )
                        },
                        onClick = {
                            vm.listGramCurrent = i
                            vm.expandedListGram = false
                            vm.conversion()
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun KgBodyOutput(
    paddingValues: PaddingValues,
    vm: KonverterGramViewModel
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .border(2.dp, LightBlue)
    )
    Surface(
        modifier = Modifier
            .background(customSwitchColor(Color.White, Color.Black))
            .padding(horizontal = 10.dp)
            .padding(top = 10.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier
                .background(customSwitchColor(Color.White, Color.Black))
                .border(
                    width = 1.dp,
                    color = customSwitchColor(Color.Gray, Color.LightGray),
                    shape = CircleShape.copy(CornerSize(5.dp))
                )
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(top = 15.dp),
        ) {
            Text(
                text = stringResource(id = R.string.teksHasil),
                fontFamily = FontFamily.SansSerif,
                color = switchColorText(),
                style = MaterialTheme.typography.headlineSmall,
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
                        vm.outputMg,
                        vm.outputCg,
                        vm.outputDg,
                        vm.outputG,
                        vm.outputDag,
                        vm.outputHg,
                        vm.outputKg
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
                    for (i in vm.listGram) {
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
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)

@Composable
private fun prev() {
    KonverterGramScreen(
        data = Gram("meter converter"),
        navController = NavController(LocalContext.current)
    )
}
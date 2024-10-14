package com.piscokz.Pengolah_rumus_compose.ui.Programs.RumusKelilingPersegiPanjang

import android.content.ClipData
import android.content.ClipboardManager
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.input.pointer.pointerInput
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.AppViewModelProvider
import com.piscokz.Pengolah_rumus_compose.Kpp
import com.piscokz.Pengolah_rumus_compose.R
import com.piscokz.Pengolah_rumus_compose.ui.Programs.customSwitchColor
import com.piscokz.Pengolah_rumus_compose.ui.Programs.switchButtonColors
import com.piscokz.Pengolah_rumus_compose.ui.Programs.switchColorText
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightBlue
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightButtonColors
import com.piscokz.Pengolah_rumus_compose.ui.theme.clearButtonDarkMode
import kotlinx.coroutines.launch

val listUkuranPanjang: List<String> = listOf("mm", "cm", "dm", "m", "dam", "hm", "km")

const val lebarTexfield = 120
const val marginBawah = 20
const val marginBawahSesi = 40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Kpp(
    data : Kpp,
    navController: NavController,
    kppViewModel: KppViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val focusRequester = remember { FocusRequester() }
    val snackbarHostState = remember { SnackbarHostState() }

//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors().copy(
                            containerColor = customSwitchColor(LightButtonColors, Color.Black),
                        ),
                        title = {
                            Text(
                                color = customSwitchColor(Color.White, Color.LightGray),
                                text = data.judul,
                                maxLines = 1,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.W600,
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
                            if (kppViewModel.inputPanjang.isNotEmpty() || kppViewModel.inputLebar.isNotEmpty()) {
                                kppViewModel.isError = false
                                kppViewModel.inputPanjang = ""
                                kppViewModel.inputLebar = ""
                            }
                            else {
                                focusRequester.requestFocus()
                                kppViewModel.isError = false
                                kppViewModel.display = ""
                            }
                        },
                        contentColor = customSwitchColor(Color.Black, Color.LightGray),
                        containerColor = customSwitchColor(
                            lighMode = Color.White,
                            darkMode = Color.DarkGray
                        )
                    ) {
                        Icon(imageVector = Icons.TwoTone.Delete, contentDescription = null)
                    }
                }

            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    if(!isSystemInDarkTheme()) {
                        Image(
                            painter = painterResource(R.drawable.img_background_multigram),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    KppBody(
//                        paddingValues = paddingValues,
                        kppViewModel = kppViewModel,
                        focusRequester = focusRequester,
                        snackbarHostState = snackbarHostState
                    )
                }
            }
//        }
}

@Composable
fun KppBody(
//    paddingValues: PaddingValues,
    kppViewModel: KppViewModel,
    focusRequester: FocusRequester,
    snackbarHostState: SnackbarHostState
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val clipboard = context.getSystemService(ClipboardManager::class.java)

    // Mengatur fokus secara otomatis ketika halaman pertama kali ditampilkan
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            focusRequester.requestFocus()
        }
    }

    // Fungsi untuk menampilkan snackbar
    fun showSnackbar() {
        coroutineScope.launch {
            snackbarHostState.showSnackbar("Copy")
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
//            .padding(paddingValues)
    ) {
        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .border(2.dp, LightBlue)
            )
            Column {
                Text(
                    text = kppViewModel.display,
                    color = customSwitchColor(Color.Black, Color.LightGray),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Thin,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .background(customSwitchColor(Color.White, Color.Black))
                        .fillMaxWidth()
                        .padding(vertical = 50.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = {
                                    if (kppViewModel.copyDisplay != "") {
                                        val clip =
                                            ClipData.newPlainText("label", kppViewModel.copyDisplay)
                                        clipboard.setPrimaryClip(clip)
                                        showSnackbar()
                                        kppViewModel.copyDisplay = ""
                                    }
                                }
                            )
                        }
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .border(2.dp, customSwitchColor(Color.LightGray, Color.Gray))
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(customSwitchColor(Color.White, Color.Black))
                    .size(marginBawah.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.background(customSwitchColor(Color.White, Color.Black))
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
                                    .trimStart { it == '0' }
                                    .replace("-", "")
                                    .replace(",", "")
                                    .replace(" ", "")
                            },
                            label = {
                                Text(
                                    color = switchColorText(),
                                    text = stringResource(id = R.string.panjang_kpp_lpp),
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    fontStyle = FontStyle.Italic
                                )
                            },
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Start
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next,
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .focusRequester(focusRequester)
//                                .padding(end = 5.dp)
                                .fillParentMaxWidth(0.4f),
                            trailingIcon = {
                                Row(
                                    modifier = Modifier
                                        .clickable {
                                            keyboardController?.hide()
                                            kppViewModel.expandedPanjang = true
                                        },
                                ) {
                                    Text(
                                        text = kppViewModel.ukuranInputPanjang,
                                        modifier = Modifier.padding(top = 2.dp)
                                    )
                                    if (kppViewModel.expandedPanjang) {
                                        Icon(
                                            tint = LightButtonColors,
                                            imageVector = Icons.Default.KeyboardArrowUp,
                                            contentDescription = null
                                        )
                                    } else {
                                        Icon(
                                            tint = LightButtonColors,
                                            imageVector = Icons.Default.KeyboardArrowDown,
                                            contentDescription = null
                                        )
                                    }
                                }
                            },
                            supportingText = {
//                                if (kppViewModel.inputPanjang.isEmpty()) Text(
//                                    text = stringResource(id = R.string.placeholderInput),
//                                    color = switchColorText()
//                                )
                                if(kppViewModel.isError && kppViewModel.inputPanjang.isEmpty()) {
                                    Text(
                                        text = "${stringResource(id = R.string.placeholderInput)} !",
                                        color = Color.Red
                                    )
                                }
                            },
                            isError = kppViewModel.inputPanjang.isEmpty() && kppViewModel.isError,
                            colors = TextFieldDefaults.colors().copy(
                                unfocusedContainerColor = customSwitchColor(
                                    Color.White,
                                    Color.Black
                                ),
                                focusedContainerColor = customSwitchColor(Color.White, Color.Black),
                                errorContainerColor = customSwitchColor(Color.White, Color.Black),
                                focusedIndicatorColor = LightBlue
                            )
                        )

                        DropdownMenu(
                            offset = DpOffset(x = (40).dp, y = 0.dp),
                            expanded = kppViewModel.expandedPanjang,
                            onDismissRequest = {
                                kppViewModel.expandedPanjang = false
                                keyboardController?.hide()
                            },
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, LightBlue),
                            containerColor = customSwitchColor(LightBlue, Color.Black),
                        ) {
                            for (i in listUkuranPanjang) {
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = i,
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Center
                                        )
                                    },
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
                            offset = DpOffset(x = (40).dp, y = 0.dp),
                            expanded = kppViewModel.expandedLebar,
                            onDismissRequest = { kppViewModel.expandedLebar = false },
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, LightBlue),
                            containerColor = customSwitchColor(LightBlue, Color.Black),
                        ) {
                            for (i in listUkuranPanjang) {
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = i,
                                            textAlign = TextAlign.Center
                                        )
                                    },
                                    onClick = {
                                        kppViewModel.ukuranInputLebar = i
                                        kppViewModel.expandedLebar = false
                                        keyboardController?.hide()
                                    })
                            }
                        }

                        OutlinedTextField(
                            value = kppViewModel.inputLebar,
                            onValueChange = { kppViewModel.inputLebar = it },
                            label = {
                                Text(
                                    color = switchColorText(),
                                    text = stringResource(id = R.string.lebar_kpp_lpp),
                                    fontFamily = FontFamily.Serif,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    fontStyle = FontStyle.Italic

                                )
                            },
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Start
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                    focusManager.clearFocus()
                                }
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .fillParentMaxWidth(0.4f)
//                                .pointerInput(key1 = true) {}
                            ,
                            trailingIcon = {
                                Row(
                                    modifier = Modifier
                                        .clickable {
                                            keyboardController?.hide()
                                            kppViewModel.expandedLebar = true
                                        },
                                ) {
                                    Text(
                                        text = kppViewModel.ukuranInputLebar,
                                        modifier = Modifier.padding(top = 2.dp)
                                    )
                                    if (kppViewModel.expandedLebar) {
                                        Icon(
                                            tint = LightButtonColors,
                                            imageVector = Icons.Default.KeyboardArrowUp,
                                            contentDescription = null
                                        )
                                    } else {
                                        Icon(
                                            tint = LightButtonColors,
                                            imageVector = Icons.Default.KeyboardArrowDown,
                                            contentDescription = null
                                        )
                                    }
                                }
                            },

                            supportingText = {
//                                if (kppViewModel.inputLebar.isEmpty()) Text(
//                                    text = stringResource(id = R.string.placeholderInput),
//                                    color = switchColorText()
//                                )
                                if(kppViewModel.isError && kppViewModel.inputPanjang.isEmpty()) {
                                    Text(
                                        text = "${stringResource(id = R.string.placeholderInput)} !",
                                        color = Color.Red
                                    )
                                }
                            },
                            isError = kppViewModel.inputLebar.isEmpty() && kppViewModel.isError,
                            colors = TextFieldDefaults.colors().copy(
                                unfocusedContainerColor = customSwitchColor(
                                    Color.White,
                                    Color.Black
                                ),
                                focusedContainerColor = customSwitchColor(Color.White, Color.Black),
                                errorContainerColor = customSwitchColor(Color.White, Color.Black),
                                focusedIndicatorColor = LightBlue
                            )
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
                        .fillMaxWidth()
                        ,
                    horizontalArrangement = Arrangement.End
                ) {
                    Row (
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Button (
                            border = BorderStroke(1.dp, LightBlue),
                            colors = switchButtonColors(),
                            onClick = {
                                if (kppViewModel.inputPanjang.count { it == '.' } > 1 ||
                                    kppViewModel.inputLebar.count { it == '.' } > 1
                                ) {
                                    kppViewModel.inputPanjang = ""
                                    kppViewModel.inputLebar = ""
                                    kppViewModel.isError = true
                                } else {
                                    kppViewModel.isError =
                                        kppViewModel.inputPanjang.isEmpty() || kppViewModel.inputLebar.isEmpty()

                                    if (kppViewModel.inputPanjang.isNotEmpty() && kppViewModel.inputLebar.isNotEmpty()) {
                                        kppViewModel.panjang = kppViewModel.inputPanjang
                                        kppViewModel.lebar = kppViewModel.inputLebar

                                        kppViewModel.panjang = kppViewModel.konversiUkuranKppPanjang()
                                        kppViewModel.lebar = kppViewModel.konversiUkuranKppLebar()

                                        kppViewModel.display = kppViewModel.hitungKpp()
//                                        kppViewModel.display = "panjang = ${kppViewModel.panjang} | lebar = ${kppViewModel.lebar}"
                                    } else {
                                        kppViewModel.isError = true
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
                        Column {
                            DropdownMenu(
                                offset = DpOffset(x = (-15).dp, y = (-40).dp),
                                expanded = kppViewModel.expandedHitung,
                                onDismissRequest = { kppViewModel.expandedHitung = false },
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.dp, LightBlue),
                                containerColor = customSwitchColor(LightBlue, Color.Black),
                            ) {
                                for (i in listUkuranPanjang) {
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = i,
                                                modifier = Modifier.fillMaxWidth(),
                                                textAlign = TextAlign.Center
                                            )
                                        },
                                        onClick = {
                                            kppViewModel.ukuranInputHitung = i
                                            kppViewModel.expandedHitung = false
                                        }
                                    )
                                }
                            }
                            Button(
                                onClick = {
                                    keyboardController?.hide()
                                    kppViewModel.expandedHitung = true
                                },
                                colors = switchButtonColors(),
                                border = BorderStroke(1.dp, LightBlue)
                            ) {
                                Row {
                                    Text(
                                        text = kppViewModel.ukuranInputHitung,
                                        modifier = Modifier.padding(top = 2.dp)
                                    )
                                    if (kppViewModel.expandedHitung) {
                                        Icon(
                                            imageVector = Icons.Default.KeyboardArrowUp,
                                            contentDescription = null
                                        )
                                    } else {
                                        Icon(
                                            imageVector = Icons.Default.KeyboardArrowDown,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
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
                        .height((0.5).dp)
                        .border((0.5).dp, switchColorText())
                )
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .background(Color.Transparent),
            ) {
                // Menampilkan Snackbar
                SnackbarHost(
                    snackbar = {
                        Snackbar(
                            modifier = Modifier,
                            containerColor = Color.DarkGray,
                            snackbarData = it,
                            contentColor = Color.LightGray
                        )
                    },
                    hostState = snackbarHostState
                )
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun Prev() {
    Kpp(
        data = Kpp("ok ags"),
        kppViewModel = KppViewModel(),
        navController = NavController(context = LocalContext.current)
    )
}

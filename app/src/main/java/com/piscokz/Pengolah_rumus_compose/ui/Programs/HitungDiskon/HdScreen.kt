package com.piscokz.Pengolah_rumus_compose.ui.Programs.HitungDiskon

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.AppViewModelProvider
import com.piscokz.Pengolah_rumus_compose.Hd
import com.piscokz.Pengolah_rumus_compose.ui.Programs.customSwitchColor
import com.piscokz.Pengolah_rumus_compose.ui.Programs.switchColorText
import com.piscokz.Pengolah_rumus_compose.ui.theme.DarkModeNote
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightBlue
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightModeNote
import com.piscokz.Pengolah_rumus_compose.ui.theme.clearButtonDarkMode
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HdScreen(
    data : Hd,
    navController: NavController,
    vm: HdViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val focusRequester = remember { FocusRequester() }
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    // Mengatur fokus secara otomatis ketika halaman pertama kali ditampilkan
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            focusRequester.requestFocus()
        }
    }


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = customSwitchColor(
                        lighMode = Color.White,
                        darkMode = Color.Black
                    )
                ),
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
                            tint = customSwitchColor(
                                lighMode = Color.Black,
                                darkMode = Color.LightGray
                            ),
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
                    if (vm.inputHarga.isNotEmpty() || vm.inputDiskon.isNotEmpty()) {
                        vm.inputHarga = ""
                        vm.inputDiskon = ""
                    } else if (vm.inputHarga.isEmpty() && vm.inputDiskon.isEmpty()) {
                        vm.harga = ""
                        vm.jumlahDiskon = ""
                        focusRequester.requestFocus()
                    }

                },
                containerColor = customSwitchColor(
                    lighMode = Color.Red,
                    darkMode = clearButtonDarkMode
                )
            ) {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.TwoTone.Delete,
                    contentDescription = null
                )
            }
        },
        containerColor = customSwitchColor(
            lighMode = Color.White, darkMode = Color.Black
        )
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
//                .background(Color.LightGray)
                .padding(horizontal = 10.dp)
//                .padding(top = 10.dp)
            ,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
//            Input harga
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                value = vm.inputHarga,
                onValueChange = { it ->
                    vm.inputHarga = it
                        .trimStart { it == '0' }
                        .replace("-", "")
                        .replace(",", "")
                        .replace(" ", "")
                        .replace(".", "")
                },
                textStyle = LocalTextStyle.current.copy(
//                    textAlign = TextAlign.Right,
                    color = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    )
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        color = customSwitchColor(
                            lighMode = Color.Gray,
                            darkMode = Color.Gray
                        ),
                        text = "Masukkan harga",
//                        textAlign = TextAlign.Right
                    )
                },
                enabled = true,
                label = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Harga",
//                        textAlign = TextAlign.Right,
                        color = customSwitchColor(
                            lighMode = Color.Black,
                            darkMode = Color.LightGray
                        )
                    )
                },
                isError = false,
                colors = TextFieldDefaults.colors().copy(
                    textSelectionColors = TextSelectionColors(
                        handleColor = customSwitchColor(
                            lighMode = Color.Black,
                            darkMode = Color.LightGray
                        ),
                        backgroundColor = customSwitchColor(
                            lighMode = LightBlue,
                            darkMode = LightModeNote
                        )
                    ),
                    cursorColor = customSwitchColor(
                        lighMode = LightBlue,
                        darkMode = LightModeNote
                    ),
                    unfocusedTextColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    focusedTextColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    errorTextColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    disabledTextColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    unfocusedContainerColor = customSwitchColor(
                        lighMode = Color.White,
                        darkMode = Color.Black
                    ),
                    focusedContainerColor = customSwitchColor(
                        lighMode = Color.White,
                        darkMode = Color.Black
                    ),
                    errorContainerColor = customSwitchColor(
                        lighMode = Color.White,
                        darkMode = Color.Black
                    ),
                    disabledContainerColor = customSwitchColor(
                        lighMode = Color.White,
                        darkMode = Color.Black
                    ),
                    unfocusedPlaceholderColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    focusedPlaceholderColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    errorPlaceholderColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    unfocusedIndicatorColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    focusedIndicatorColor = customSwitchColor(
                        lighMode = LightModeNote,
                        darkMode = LightBlue
                    ),
                    disabledIndicatorColor = customSwitchColor(
                        lighMode = Color.White,
                        darkMode = Color.Black
                    ),
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number,
                ),
                keyboardActions = KeyboardActions(
//                    onNext = {},
                    onSend = {
                        vm.hitungDiskon()
                    }
                )
            )
//            input diskon
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = vm.inputDiskon,
                onValueChange = { it ->
                    if (vm.inputDiskon.length < 2 || it.length < vm.inputDiskonSebelumnya.length) {
                        vm.inputDiskonSebelumnya = it
                        vm.inputDiskon = it
                            .trimStart { it == '0' }
                            .replace("-", "")
                            .replace(",", "")
                            .replace(".", "")
                            .replace(" ", "")
                    }

                },
                textStyle = LocalTextStyle.current.copy(
//                    textAlign = TextAlign.Right,
                    color = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    )
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        color = customSwitchColor(
                            lighMode = Color.Gray,
                            darkMode = Color.Gray
                        ),
                        text = "Masukkan harga",
//                        textAlign = TextAlign.Right
                    )
                },
                enabled = true,
                trailingIcon = { Text(text = "%", color = customSwitchColor(
                    lighMode = Color.Black,
                    darkMode = Color.LightGray
                ))},
                label = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Diskon",
//                        textAlign = TextAlign.Right,
                        color = customSwitchColor(
                            lighMode = Color.Black,
                            darkMode = Color.LightGray
                        )
                    )
                },
                isError = false,
                colors = TextFieldDefaults.colors().copy(
                    textSelectionColors = TextSelectionColors(
                        handleColor = customSwitchColor(
                            lighMode = Color.Black,
                            darkMode = Color.LightGray
                        ),
                        backgroundColor = customSwitchColor(
                            lighMode = LightBlue,
                            darkMode = LightModeNote
                        )
                    ),
                    cursorColor = customSwitchColor(
                        lighMode = LightBlue,
                        darkMode = LightModeNote
                    ),
                    unfocusedTextColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    focusedTextColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    errorTextColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    disabledTextColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    unfocusedContainerColor = customSwitchColor(
                        lighMode = Color.White,
                        darkMode = Color.Black
                    ),
                    focusedContainerColor = customSwitchColor(
                        lighMode = Color.White,
                        darkMode = Color.Black
                    ),
                    errorContainerColor = customSwitchColor(
                        lighMode = Color.White,
                        darkMode = Color.Black
                    ),
                    disabledContainerColor = customSwitchColor(
                        lighMode = Color.White,
                        darkMode = Color.Black
                    ),
                    unfocusedPlaceholderColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    focusedPlaceholderColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    errorPlaceholderColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    unfocusedIndicatorColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    ),
                    focusedIndicatorColor = customSwitchColor(
                        lighMode = LightModeNote,
                        darkMode = LightBlue
                    ),
                    disabledIndicatorColor = customSwitchColor(
                        lighMode = Color.White,
                        darkMode = Color.Black
                    ),
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send,
                    keyboardType = KeyboardType.Number,
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        if (vm.inputHarga.isNotEmpty() && vm.inputDiskon.isNotEmpty()) {
                            vm.hitungDiskon()
                            focusManager.clearFocus()
                        }
                    }
                )
            )
            if (vm.harga.isNotEmpty() && vm.jumlahDiskon.isNotEmpty()) {
//            harga setelah diskon
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = vm.harga,
                    onValueChange = { it ->
                        vm.inputDiskon = it
                            .trimStart { it == '0' }
                            .replace("-", "")
                            .replace(",", "")
                            .replace(" ", "")
                            .replace(".", "")
                    },
                    textStyle = LocalTextStyle.current.copy(
//                    textAlign = TextAlign.Right,
                        color = customSwitchColor(
                            lighMode = Color.Black,
                            darkMode = Color.LightGray
                        )
                    ),
                    singleLine = true,
                    readOnly = true,
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Harga setelah diskon",
//                        textAlign = TextAlign.Right,
                            color = Color.Gray
                        )
                    },
                    isError = false,
                    colors = TextFieldDefaults.colors().copy(
                        textSelectionColors = TextSelectionColors(
                            handleColor = DarkModeNote,
                            backgroundColor = customSwitchColor(
                                lighMode = LightModeNote,
                                darkMode = Color.DarkGray
                            )
                        ),
                        cursorColor = customSwitchColor(
                            lighMode = LightBlue,
                            darkMode = LightModeNote
                        ),
                        unfocusedTextColor = customSwitchColor(
                            lighMode = Color.Black,
                            darkMode = Color.LightGray
                        ),
                        focusedTextColor = customSwitchColor(
                            lighMode = Color.Black,
                            darkMode = Color.LightGray
                        ),
                        unfocusedContainerColor = customSwitchColor(
                            lighMode = Color.White,
                            darkMode = Color.Black
                        ),
                        focusedContainerColor = customSwitchColor(
                            lighMode = Color.White,
                            darkMode = Color.Black
                        ),
                        unfocusedPlaceholderColor = customSwitchColor(
                            lighMode = Color.Black,
                            darkMode = Color.LightGray
                        ),
                        focusedPlaceholderColor = customSwitchColor(
                            lighMode = Color.Black,
                            darkMode = Color.LightGray
                        ),
                        unfocusedIndicatorColor = customSwitchColor(
                            lighMode = Color.White,
                            darkMode = Color.Black
                        ),
                        focusedIndicatorColor = customSwitchColor(
                            lighMode = Color.White,
                            darkMode = Color.Black
                        )
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Send,
                        keyboardType = KeyboardType.Number,
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {
                            if (vm.inputHarga.isNotEmpty() && vm.inputDiskon.isNotEmpty()) {
                                vm.hitungDiskon()
                                focusManager.clearFocus()
                            }
                        }
                    )
                )
//            harga setelah diskon
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = vm.jumlahDiskon,
                    onValueChange = { it ->
                        vm.inputDiskon = it
                            .trimStart { it == '0' }
                            .replace("-", "")
                            .replace(",", "")
                            .replace(" ", "")
                            .replace(".", "")
                    },
                    textStyle = LocalTextStyle.current.copy(
//                        textAlign = TextAlign.Right,
                        color = customSwitchColor(
                            lighMode = Color.Black,
                            darkMode = Color.LightGray
                        )
                    ),
                    singleLine = true,
                    readOnly = true,
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Anda hemat",
//                            textAlign = TextAlign.Right,
                            color = Color.Gray
                        )
                    },
                    isError = false,
                    colors = TextFieldDefaults.colors().copy(
                        textSelectionColors = TextSelectionColors(
                            handleColor = DarkModeNote,
                            backgroundColor = customSwitchColor(
                                lighMode = LightModeNote,
                                darkMode = Color.DarkGray
                            )
                        ),
                        cursorColor = customSwitchColor(
                            lighMode = LightBlue,
                            darkMode = LightModeNote
                        ),
                        unfocusedTextColor = customSwitchColor(
                            lighMode = Color.Black,
                            darkMode = Color.LightGray
                        ),
                        focusedTextColor = customSwitchColor(
                            lighMode = Color.Black,
                            darkMode = Color.LightGray
                        ),
                        unfocusedContainerColor = customSwitchColor(
                            lighMode = Color.White,
                            darkMode = Color.Black
                        ),
                        focusedContainerColor = customSwitchColor(
                            lighMode = Color.White,
                            darkMode = Color.Black
                        ),
                        unfocusedPlaceholderColor = customSwitchColor(
                            lighMode = Color.Black,
                            darkMode = Color.LightGray
                        ),
                        focusedPlaceholderColor = customSwitchColor(
                            lighMode = Color.Black,
                            darkMode = Color.LightGray
                        ),
                        unfocusedIndicatorColor = customSwitchColor(
                            lighMode = Color.White,
                            darkMode = Color.Black
                        ),
                        focusedIndicatorColor = customSwitchColor(
                            lighMode = Color.White,
                            darkMode = Color.Black
                        )
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Send,
                        keyboardType = KeyboardType.Number,
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {
                            if (vm.inputHarga.isNotEmpty() && vm.inputDiskon.isNotEmpty()) {
                                vm.hitungDiskon()
                                focusManager.clearFocus()
                            }
                        }
                    )
                )
            }

        }

    }
}

@Composable
fun Hasil() {
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
            color = customSwitchColor(lighMode = Color.Black, darkMode = Color.LightGray),
            fontWeight = FontWeight.W300,
            style = MaterialTheme.typography.titleLarge,
            letterSpacing = TextUnit(2.5f, TextUnitType.Sp)
        )
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HdScreenPrev() {
    HdScreen(data = Hd("Diskon"), navController = NavController(context = LocalContext.current))
}
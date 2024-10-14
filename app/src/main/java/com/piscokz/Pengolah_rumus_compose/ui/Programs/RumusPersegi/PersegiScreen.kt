package com.piscokz.Pengolah_rumus_compose.ui.Programs.RumusPersegi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.AppViewModelProvider
import com.piscokz.Pengolah_rumus_compose.R
import com.piscokz.Pengolah_rumus_compose.Square
import com.piscokz.Pengolah_rumus_compose.ui.Programs.customSwitchColor
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightBlue
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightButtonColors
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightModeNote
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersegiScreen(
    data : Square,
    navController: NavController,
    vm: PersegiViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val focusRequester = remember { FocusRequester() }
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    // Mengatur fokus secara otomatis ketika halaman pertama kali ditampilkan
//    LaunchedEffect(Unit) {
//        coroutineScope.launch {
//            focusRequester.requestFocus()
//        }
//    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = customSwitchColor(
                        lighMode = LightButtonColors,
                        darkMode = Color.Black
                    )
                ),
                title = {
                    Text(
                        color = customSwitchColor(Color.White, Color.LightGray),
                        text = data.judul,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.W600,
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            tint = customSwitchColor(
                                lighMode = Color.White,
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
//                    if (vm.inputHarga.isNotEmpty() || vm.inputDiskon.isNotEmpty()) {
//                        vm.inputHarga = ""
//                        vm.inputDiskon = ""
//                    } else if (vm.inputHarga.isEmpty() && vm.inputDiskon.isEmpty()) {
//                        vm.harga = ""
//                        vm.jumlahDiskon = ""
//                        focusRequester.requestFocus()
//                    }

                },
                containerColor = customSwitchColor(
                    lighMode = Color.White,
                    darkMode = Color.DarkGray
                )
            ) {
                Icon(
                    tint = customSwitchColor(Color.Black, Color.LightGray),
                    imageVector = Icons.TwoTone.Delete,
                    contentDescription = null
                )
            }
        },
        containerColor = customSwitchColor(
            lighMode = Color.White, darkMode = Color.Black
        )
    ) { paddingValues ->
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
//                shape = RoundedCornerShape(15.dp),
                color = customSwitchColor(Color.White, Color.Black),
                modifier = Modifier
//                .padding(top = 50.dp)
//                .padding(horizontal = 10.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .border(2.dp, LightBlue)
                )
                Column(
                    modifier = Modifier
//                        .border(1.dp, customSwitchColor(Color.White, Color.White), shape = RoundedCornerShape(15.dp))
                        .padding(vertical = 15.dp)
                        .padding(horizontal = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {}
//                        { it ->
//                            if (vm.inputDiskon.length < 2 || it.length < vm.inputDiskonSebelumnya.length) {
//                                vm.inputDiskonSebelumnya = it
//                                vm.inputDiskon = it
//                                    .trimStart { it == '0' }
//                                    .replace("-", "")
//                                    .replace(",", "")
//                                    .replace(".", "")
//                                    .replace(" ", "")
//                            }
//
//                        }
                        ,
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
                                text = stringResource(R.string.perintahDiskon),
//                        textAlign = TextAlign.Right
                            )
                        },
                        enabled = true,
                        trailingIcon = {
                            Text(
                                text = "%", color = customSwitchColor(
                                    lighMode = Color.Black,
                                    darkMode = Color.LightGray
                                )
                            )
                        },
                        label = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(R.string.diskon),
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
//                            onSend = {
//                                if (vm.inputHarga.isNotEmpty() && vm.inputDiskon.isNotEmpty()) {
//                                    vm.hitungDiskon()
//                                    focusManager.clearFocus()
//                                }
//                            }
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun prev() {
    PersegiScreen(data = Square(""), navController = NavController(LocalContext.current))
}
package com.piscokz.Pengolah_rumus_compose

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.ui.Programs.RumusLuasPersegiPanjang.LppViewModel
import com.piscokz.Pengolah_rumus_compose.ui.Programs.customSwitchColor
import com.piscokz.Pengolah_rumus_compose.ui.Programs.switchButtonColors
import com.piscokz.Pengolah_rumus_compose.ui.Programs.switchColorText
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightBlue
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightButtonColors
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightModeNote
import com.piscokz.Pengolah_rumus_compose.ui.theme.clearButtonDarkMode
import kotlinx.coroutines.launch

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
    val focusRequester : FocusRequester = remember { FocusRequester() }
    val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
//            modifier = Modifier.border((0.5).dp, LightBlue),
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
                        if (lppViewModel.inputPanjang.isNotEmpty() || lppViewModel.inputLebar.isNotEmpty()) {
                            lppViewModel.isError = false
                            lppViewModel.inputPanjang = ""
                            lppViewModel.inputLebar = ""
                        } else {
                            focusRequester.requestFocus()
                            lppViewModel.isError = false
                            lppViewModel.display = ""

                        }
                    },
                    contentColor = customSwitchColor(lighMode = Color.Black, darkMode = Color.LightGray),
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
                LppBody(
//                paddingValues = paddingValues,
                    lppViewModel = lppViewModel,
                    focusRequester = focusRequester,
                    snackbarHostState = snackbarHostState
                )
            }
        }
    }

@Composable
fun LppBody(
//    paddingValues: PaddingValues,
    lppViewModel: LppViewModel,
    focusRequester: FocusRequester,
    snackbarHostState : SnackbarHostState
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
//            snackbarHostState.showSnackbar("Copy")
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
                    text = lppViewModel.display,
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
                                    if (lppViewModel.copyDisplay != "") {
                                        val clip =
                                            ClipData.newPlainText("label", lppViewModel.copyDisplay)
                                        clipboard.setPrimaryClip(clip)
                                        showSnackbar()
                                        lppViewModel.copyDisplay = ""
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
                            value = lppViewModel.inputPanjang,
                            onValueChange = {
                                lppViewModel.inputPanjang = it
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
                                imeAction = ImeAction.Next
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .focusRequester(focusRequester = focusRequester)
//                                .padding(end = 5.dp)
                                .fillParentMaxWidth(0.4f)
                            ,
                            trailingIcon = {
                                Row(
                                    modifier = Modifier.clickable {
                                        keyboardController?.hide()
                                        lppViewModel.expandedPanjang = true
                                    }
                                ) {
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
                                    if (lppViewModel.expandedPanjang) {
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
//                                if (lppViewModel.inputPanjang.isEmpty()) Text(
//                                    text = stringResource(id = R.string.placeholderInput),
//                                    color = switchColorText()
//                                )
                                if (lppViewModel.isError && lppViewModel.inputPanjang.isEmpty()) {
                                    Text(
                                        text = "${stringResource(id = R.string.placeholderInput)} !",
                                        color = Color.Red
                                    )
                                }
                            },
                            isError = lppViewModel.inputPanjang.isEmpty() && lppViewModel.isError,
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
                            expanded = lppViewModel.expandedPanjang,
                            onDismissRequest = { lppViewModel.expandedPanjang = false },
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, LightBlue),
                            containerColor = customSwitchColor(LightBlue, Color.Black)
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
                                        lppViewModel.ukuranInputPanjang = i
                                        lppViewModel.expandedPanjang = false
                                        keyboardController?.hide()
                                    })
                            }
                        }
                    }
                    Row {
                        DropdownMenu(
                            offset = DpOffset(x = (40).dp, y = 0.dp),
                            expanded = lppViewModel.expandedLebar,
                            onDismissRequest = { lppViewModel.expandedLebar = false },
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, LightBlue),
                            containerColor = customSwitchColor(LightBlue, Color.Black)
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
                                        lppViewModel.ukuranInputLebar = i
                                        lppViewModel.expandedLebar = false
                                        keyboardController?.hide()
                                    })
                            }
                        }
                        OutlinedTextField(
                            value = lppViewModel.inputLebar,
                            onValueChange = { lppViewModel.inputLebar = it },
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
                            keyboardActions =
                                KeyboardActions(
                                    onDone = {
                                        keyboardController?.hide()
                                        focusManager.clearFocus()
                                    }
                                )
                            ,
                            singleLine = true,
                            modifier = Modifier
//                                .padding(start = 5.dp)
                                .fillParentMaxWidth(0.4f)
                            ,
                            trailingIcon = {
                                Row(
                                    modifier = Modifier
                                        .clickable {
                                            keyboardController?.hide()
                                            lppViewModel.expandedLebar = true
                                        }
                                ) {
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
                                    if (lppViewModel.expandedLebar) {
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
//                                if (lppViewModel.inputLebar.isEmpty()) Text(
//                                    text = stringResource(id = R.string.placeholderInput),
//                                    color = switchColorText()
//                                )
                                if (lppViewModel.isError && lppViewModel.inputLebar.isEmpty()) Text(
                                    text = "${stringResource(id = R.string.placeholderInput)} !",
                                    color = Color.Red
                                )
                            },
                            isError = lppViewModel.inputLebar.isEmpty() && lppViewModel.isError,
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
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Button(
                            border = BorderStroke(1.dp, LightBlue),
                            colors = switchButtonColors(),
                            onClick = {
                                if (lppViewModel.inputPanjang.count { it == '.' } > 1 ||
                                    lppViewModel.inputLebar.count { it == '.' } > 1
                                ) {
                                    lppViewModel.inputPanjang = ""
                                    lppViewModel.inputLebar = ""
                                    lppViewModel.isError = true
                                } else {
                                    lppViewModel.isError = lppViewModel.inputPanjang.isEmpty() || lppViewModel.inputLebar.isEmpty()
                                    if (lppViewModel.inputPanjang.isNotEmpty() && lppViewModel.inputLebar.isNotEmpty()) {
                                        lppViewModel.panjang = lppViewModel.inputPanjang
                                        lppViewModel.lebar = lppViewModel.inputLebar

//                                        lppViewModel.panjang = lppViewModel.konversiUkuranLppPanjang()
                                        lppViewModel.lebar = lppViewModel.konversiUkuranLppLebar()
                                        lppViewModel.display = lppViewModel.hitungLpp()
                                    } else {
                                        lppViewModel.isError = true
                                    }
                                }
                            }
                        ) {
                            Text(
                                color = Color.White,
                                text = stringResource(id = R.string.teksHitung_kpp_lpp),
                                textAlign = TextAlign.Center
                            )
                        }
                        Column {
                            DropdownMenu(
                                expanded = lppViewModel.expandedHitung,
                                onDismissRequest = { lppViewModel.expandedHitung = false },
                                offset = DpOffset(x = (-15).dp, y = (-40).dp),
                                shape = RoundedCornerShape(10.dp),
                                border = BorderStroke(1.dp, LightBlue),
                                containerColor = customSwitchColor(LightBlue, Color.Black),
                            ) {
                                for (i in listUkuranPanjang) {
                                    DropdownMenuItem(
                                        text = { Text(
                                            text = i,
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Center
                                        ) },
                                        onClick = {
                                            lppViewModel.ukuranInputHitung = i
                                            lppViewModel.expandedHitung = false
                                        },
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
                                            Icons.Default.KeyboardArrowUp,
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
                    .background(Color.White),
            ) {
                // Menampilkan Snackbar
                SnackbarHost(
                    snackbar = {
                        Snackbar(
                            modifier = Modifier,
                            containerColor = LightBlue,
                            snackbarData = it,
                            contentColor = Color.Black
                        )
                    },
                    hostState = snackbarHostState
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
    Lpp(
        data = Lpp("lpp"),
        lppViewModel = LppViewModel(),
        navController = NavController(context = LocalContext.current)
    )
}

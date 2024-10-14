package com.piscokz.Pengolah_rumus_compose.ui.Programs.Calculator

import android.content.res.Configuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.Calculator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.piscokz.Pengolah_rumus_compose.AppViewModelProvider
import com.piscokz.Pengolah_rumus_compose.ui.Programs.customSwitchColor
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightBlue
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightButtonColors
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightModeNote
import com.piscokz.Pengolah_rumus_compose.R

@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    textStyle: TextStyle = TextStyle(),
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .then(modifier)
    ) {
        Text(
            text = symbol,
            style = textStyle,
            fontSize = 36.sp,
            color = Color.White,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(
    data : Calculator,
    navController : NavController
) {
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
        containerColor = customSwitchColor(
            lighMode = Color.White, darkMode = Color.Black
        )
    ) { paddingValues ->
        paddingValues
        CalculatorBody(paddingValues = paddingValues)
    }
}

@Composable
fun CalculatorBody(viewModel: CalculatorViewModel = viewModel (factory = AppViewModelProvider.Factory), paddingValues: PaddingValues) {
    val expression = viewModel.expression
    val buttonSpacing = 8.dp

    Log.d("MainActivity", "onCreate: ${viewModel.expression.value}")
    Column(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        HorizontalDivider(
            color = LightBlue,
            thickness = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
//                .padding(paddingValues = paddingValues)
                .background(customSwitchColor(Color.White, Color.Black)),
            contentAlignment = Alignment.BottomCenter
        ) {
//        if (!isSystemInDarkTheme()) {
//            Image(
//                modifier = Modifier.fillMaxSize(),
//                painter = painterResource(R.drawable.img_background_multigram),
//                contentDescription = null,
//                contentScale = ContentScale.FillBounds
//            )
//        }
            Column(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.spacedBy(buttonSpacing),
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(customSwitchColor(Color.White, Color.Black))
                    ,
                    reverseLayout = true
                ) {
                    item {
                        Text(
                            text = expression.value,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 32.dp, horizontal = 8.dp),
                            fontWeight = FontWeight.Light,
                            fontSize = 80.sp,
                            color = customSwitchColor(Color.Black, Color.LightGray),
                            maxLines = 1
                        )
                    }
                }
                HorizontalDivider(
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "AC",
                        color = Color.Red,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.clear()
                            }
                    )

                    CalculatorButton(
                        symbol = "(",
                        color = LightModeNote,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("(")
                            }
                    )
                    CalculatorButton(
                        symbol = ")",
                        color = LightModeNote,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append(")")
                            }
                    )

                    CalculatorButton(
                        symbol = "÷",
                        color = LightModeNote,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("÷")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "7",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("7")
                            }
                    )
                    CalculatorButton(
                        symbol = "8",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("8")
                            }
                    )
                    CalculatorButton(
                        symbol = "9",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("9")
                            }
                    )
                    CalculatorButton(
                        symbol = "×",
                        color = LightModeNote,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("×")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "4",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("4")
                            }
                    )
                    CalculatorButton(
                        symbol = "5",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("5")
                            }
                    )
                    CalculatorButton(
                        symbol = "6",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("6")
                            }
                    )
                    CalculatorButton(
                        symbol = "-",
                        color = LightModeNote,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("-")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "1",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("1")
                            }
                    )
                    CalculatorButton(
                        symbol = "2",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)

                            .clickable {
                                viewModel.append("2")
                            }
                    )
                    CalculatorButton(
                        symbol = "3",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("3")
                            }
                    )
                    CalculatorButton(
                        symbol = "+",
                        color = LightModeNote,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("+")
                            }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "0",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append("0")
                            }
                    )
                    CalculatorButton(
                        symbol = ".",
                        color = Color.Gray,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.append(".")
                            }
                    )
                    CalculatorButton(
                        symbol = "Del",
                        color = Color.Red,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.delete()
                            }
                    )
                    CalculatorButton(
                        symbol = "=",
                        color = LightModeNote,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                            .clickable {
                                viewModel.evaluate()
                            }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun CalculatorPrev() {
    CalculatorScreen(data = Calculator("kalkulator"), navController = NavController(LocalContext.current))
}

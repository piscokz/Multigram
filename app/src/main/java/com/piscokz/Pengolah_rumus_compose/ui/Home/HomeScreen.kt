package com.piscokz.Pengolah_rumus_compose.ui.Home

import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.BMI
import com.piscokz.Pengolah_rumus_compose.Calculator
import com.piscokz.Pengolah_rumus_compose.Circle
import com.piscokz.Pengolah_rumus_compose.Gram
import com.piscokz.Pengolah_rumus_compose.Hd
import com.piscokz.Pengolah_rumus_compose.Kb
import com.piscokz.Pengolah_rumus_compose.Km
import com.piscokz.Pengolah_rumus_compose.Kpp
import com.piscokz.Pengolah_rumus_compose.Lpp
import com.piscokz.Pengolah_rumus_compose.R
import com.piscokz.Pengolah_rumus_compose.Square
import com.piscokz.Pengolah_rumus_compose.ui.Programs.customSwitchColor
import com.piscokz.Pengolah_rumus_compose.ui.Programs.switchColorText
import com.piscokz.Pengolah_rumus_compose.ui.Programs.switchIconButtonColors
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightBlue
import com.piscokz.Pengolah_rumus_compose.ui.theme.multigramTheme

//data class BottomNavItem (
//    val title : String,
//    val selectedIcon : Int,
//    val unselectedIcon : Int,
//
//    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController,
) {
//    val bottomNavItems = listOf(
//        BottomNavItem(
//            title = "List program",
//            selectedIcon = R.drawable.listprograms,
//            unselectedIcon = R.drawable.listprograms
//        ),
//        BottomNavItem(
//            title = "Kalkulator",
//            selectedIcon = R.drawable.calculator,
//            unselectedIcon = R.drawable.calculator
//        )
//    )
    var selected by remember { mutableIntStateOf(0) }
    multigramTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
//                .background(customSwitchColor(Color.White, Color.Red))
                ,
            topBar = {
                TopAppBar(
//                    scrollBehavior = ,
                    colors = TopAppBarDefaults.topAppBarColors().copy(
                        containerColor = customSwitchColor(Color.Transparent, Color.Black)
                    ),
                    title = {
                        Row {
                            Text(
                                "Multi",
                                color = customSwitchColor(
                                    lighMode = Color.White,
                                    darkMode = Color.LightGray
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                "Gram",
                                color = customSwitchColor(
                                    lighMode = Color.White,
                                    darkMode = LightBlue
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                    },
                    actions = {
//                        IconButton(
//                            onClick = {
//                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                            }
//                        ) {
//                            Icon(Icons.TwoTone.Face, null)
//                        }
                    }
                )
            },
//            bottomBar = {
//                NavigationBar (
//                    containerColor = customSwitchColor(Color.White, DarkBackground)
//                    ,
//                ) {
//                    bottomNavItems.forEachIndexed { index, bottomNavItem ->
//                        NavigationBarItem(
//                            alwaysShowLabel = true,
//                            colors = NavigationBarItemDefaults.colors(
//                                selectedIconColor = Color.White,
//                                unselectedIconColor = Color.Gray,
//                                indicatorColor = LightModeNote
//                            ),
//                            selected = index == selected,
//                            onClick = {
//                                selected = index
//                                if (index == 1) { navController.navigate(Calculator("kalkulator")) }
//                            },
//                            icon = {
//                                Icon(
//                                    painter = painterResource(
//                                        if (index == selected)
//                                            bottomNavItem.selectedIcon else
//                                            bottomNavItem.unselectedIcon)
//                                    ,
//                                    contentDescription = bottomNavItem.title
//                                )
//                            },
//                            label = {Text(
//                                text = bottomNavItem.title,
//                                color = customSwitchColor(Color.Black, Color.LightGray)
//                            )}
//                        )
//                    }
//                }
//            }

        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().background(customSwitchColor(Color.Transparent, Color.Black))) {
                if(!isSystemInDarkTheme()) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(R.drawable.img_background_multigram),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null
                    )
                }
                HomeBody(
                    paddingValues = paddingValues,
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun listProgram(
    namaProgram: String,
    jenisProgram: String,
    id_resource: Int,
    modifier: Modifier = Modifier,
    onClick : () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(25.dp),
        shadowElevation = 5.dp,
        color = LightBlue,
        modifier = Modifier.padding(bottom = 20.dp),
    ) {
        OutlinedIconButton(
            shape = RoundedCornerShape(25.dp),
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(160.dp)
                .padding(top = if (isSystemInDarkTheme()) 5.dp else 0.dp)
                ,
            colors = switchIconButtonColors(),
            border = BorderStroke(1.dp, Color.Transparent)
        ) {
            Row(
                modifier = modifier
                    .widthIn(min = 450.dp, max = 700.dp)
                    .height(150.dp)
                    .background(if (isSystemInDarkTheme()) Color.Black else LightBlue)
                    .padding(start = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = jenisProgram,
                        color = switchColorText(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Serif
                    )
                    val text = namaProgram.replaceFirstChar { it.titlecase() }
                    Text(
                        text =
                        if (text.length >= 20) {
                            text.substringBeforeLast(" ") + "\n" + text.substringAfterLast(" ")

                        } else text
                        ,
                        fontSize = 23.sp,
                        color = switchColorText(),
                        style = MaterialTheme.typography.headlineSmall,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium
                    )
                }
                Column() {
                    Image(painter = painterResource(id = id_resource), contentDescription = null)
                }
            }
        }

    }
}

@Composable
fun HomeBody(
    paddingValues: PaddingValues,
    navController: NavController,
) {
    val listProgram: Array<String> = stringArrayResource(R.array.listProgram)
    val listjenisProgram: Array<String> = stringArrayResource(R.array.jenisProgram)
    LazyColumn(
        modifier = Modifier
//            .background(customSwitchColor(Color.White, Color.Black))
            .padding(paddingValues)
            .padding(top = 10.dp)
            .fillMaxSize()
        ,
    ) {
        item {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
//                Text(
//                    color = switchColorText(),
//                    text = stringResource(R.string.app_name),
//                    style = MaterialTheme.typography.headlineSmall,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(start = 5.dp, bottom = 15.dp)
//                )

                listProgram(
                    namaProgram = listProgram[5],
                    jenisProgram = "program",
                    id_resource = R.drawable.calculator,
                    modifier = Modifier.padding(end = 15.dp),
                    onClick = {
                        navController.navigate(
                            Calculator(judul = listProgram[5])
                        )
                    }
                )
                listProgram(
                    namaProgram = listProgram[7],
                    jenisProgram = listjenisProgram[0],
                    id_resource = R.drawable.scale_weight,
                    modifier = Modifier.padding(end = 15.dp),
                    onClick = {
                        navController.navigate(
                            BMI(judul = listProgram[7])
                        )
                    }
                )
                listProgram(
                    namaProgram = listProgram[4],
                    jenisProgram = listjenisProgram[0],
                    id_resource = R.drawable.discount,
                    modifier = Modifier.padding(end = 15.dp),
                    onClick = {
                        navController.navigate(
                            Hd(
                                listProgram[4]
                            )
                        )
                    }
                )
                listProgram(
                    namaProgram = listProgram[6],
                    jenisProgram = listjenisProgram[1],
                    id_resource = R.drawable.gram,
                    modifier = Modifier.padding(end = 15.dp),
                    onClick = {
                        navController.navigate(
                            Gram(judul = listProgram[6])
                        )
                    }
                )
            }
        }
        item {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
//                Text(
//                    color = switchColorText(),
//                    text = myStringArray[1],
//                    style = MaterialTheme.typography.headlineMedium,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(start = 5.dp, bottom = 15.dp)
//                )
                listProgram(
                    namaProgram = listProgram[0],
                    jenisProgram = listjenisProgram[1],
                    id_resource = R.drawable.byte_logo,
                    modifier = Modifier.padding(end = 15.dp),
                    onClick = {
                        navController.navigate(
                            Kb(
                                listProgram[0]
                            )
                        )
                    }
                )
                listProgram(
                    namaProgram = listProgram[1],
                    jenisProgram = listjenisProgram[1],
                    id_resource = R.drawable.meter_logo,
                    modifier = Modifier.padding(end = 15.dp),
                    onClick = {
                        navController.navigate(
                            Km(listProgram[1])
                        )
                    }
                )
//                listProgram(
//                    namaProgram = listProgram[8],
//                    jenisProgram = listjenisProgram[0],
//                    id_resource = R.drawable.circle,
//                    modifier = Modifier.padding(end = 15.dp),
//                    onClick = {
//                        navController.navigate(
//                            Circle(judul = listProgram[8])
//                        )
//                    }
//                )
//                listProgram(
//                    namaProgram = listProgram[9],
//                    jenisProgram = listjenisProgram[0],
//                    id_resource = R.drawable.square,
//                    modifier = Modifier.padding(end = 15.dp),
//                    onClick = {
//                        navController.navigate(
//                            Square(judul = listProgram[9])
//                        )
//                    }
//                )
                listProgram(
                    namaProgram = listProgram[2],
                    jenisProgram = listjenisProgram[0],
                    id_resource = R.drawable.lpp_logo,
                    modifier = Modifier.padding(end = 15.dp),
                    onClick = {
                        navController.navigate(
                            Lpp(listProgram[2])
                        )
                    }
                )
                listProgram(
                    namaProgram = listProgram[3],
                    jenisProgram = listjenisProgram[0],
                    id_resource = R.drawable.rectangle_shape,
                    modifier = Modifier.padding(end = 15.dp),
                    onClick = {
                        navController.navigate(
                            Kpp(listProgram[3])
                        )
                    }
                )
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
    Home(navController = NavController(context = LocalContext.current))
}
package com.piscokz.Pengolah_rumus_compose.Home

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.Programs.switchColorText
import com.piscokz.Pengolah_rumus_compose.R
import com.piscokz.Pengolah_rumus_compose.ui.theme.PengolahRumusComposeTheme
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController
) {
    PengolahRumusComposeTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "MultiGram",
                            color = switchColorText(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.ExtraBold
                        )
                    },
                )

            }

        ) { paddingValues ->
            HomeBody(
                paddingValues = paddingValues,
                navController = navController
            )
        }
    }
}

@Composable
fun listProgram(
    navController: NavController,
    namaProgram: String,
    jenisProgram: String,
    screen_route: String,
    id_resource: Int,
    modifier: Modifier = Modifier
) {

    Surface(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier.padding(bottom = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable { navController.navigate(screen_route) }
                .widthIn(min = 450.dp, max = 700.dp)
                .height(150.dp)
                .padding(horizontal = 10.dp)
                .padding(end = 15.dp)
                .background(MaterialTheme.colorScheme.surface),
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
                Text(
                    text = namaProgram.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    color = switchColorText(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium
                )
            }
            Column(
            ) {
                Image(painter = painterResource(id = id_resource), contentDescription = null)
            }
        }
    }
}

@Composable
fun HomeBody(
    paddingValues: PaddingValues,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 10.dp, vertical = 15.dp)
            .fillMaxSize(),
    ) {
        item {
            Column() {
                Text(
                    modifier = Modifier.padding(start = 5.dp, bottom = 15.dp),
                    color = switchColorText(),
                    text = "Rekomendasi ",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )
                listProgram(
                    navController = navController,
                    namaProgram = "ukuran pajang",
                    jenisProgram = "konversi",
                    screen_route = "ukuran_panjang",
                    id_resource = R.drawable.meter_logo
                )
            }
        }
        item {
            Column {
                Text(
                    color = switchColorText(),
                    text = "Lainnya ",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 5.dp, bottom = 15.dp)
                )
                listProgram(
                    navController = navController,
                    namaProgram = "luas persegi panjang",
                    jenisProgram = "rumus",
                    screen_route = "lp_screen",
                    id_resource = R.drawable.rectangle_shape,
//                    modifier = Modifier.padding(end = 10.dp)
                )
            }

            listProgram(
                navController = navController,
                namaProgram = "keliling persegi panjang",
                jenisProgram = "rumus",
                screen_route = "kpp_screen",
                id_resource = R.drawable.rectangle_shape,
//                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "dark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "light"
)
@Composable
private fun prevHomeScreen() {
//    HomeBody(paddingValues = PaddingValues())
}

package com.piscokz.Pengolah_rumus_compose


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.ui.theme.PengolahRumusComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController
) {


}

@Preview
@Composable
fun Prev() {
    PengolahRumusComposeTheme {
        Column (
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ){

            Surface(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .requiredHeight(200.dp)
            ) {
                Row(modifier = Modifier.padding(24.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Hello, ")
                        Text(text = "aa")
                    }
//                    Image(
//                        painter = painterResource(id = R.drawable.persegi),
//                        contentDescription = "pesregi",
//                        modifier = Modifier
//                            .width(10.dp)
//                            .height(10.dp)
//
//                    )
                }
            }
        }
    }
}
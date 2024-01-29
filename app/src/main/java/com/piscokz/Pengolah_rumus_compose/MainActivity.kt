package com.piscokz.Pengolah_rumus_compose

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piscokz.Pengolah_rumus_compose.ui.theme.PengolahRumusComposeTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.text.style.TextOverflow

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PengolahRumusComposeTheme {
                Surface {
                    MyApp(modifier = Modifier.fillMaxSize())
                }
            }
        }

    }


    @Composable
    fun Tampilan(nama: String) {
        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .clickable {
                    var Intent = Intent(applicationContext, Rumus::class.java)
                    startActivity(Intent)
                }
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Rumus", style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = nama,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                    )
                }
//                Column (
//                    modifier = Modifier
//                        .fillMaxWidth(1f)
//                        .padding(top = 25.dp),
//                    horizontalAlignment = Alignment.End
//                ){
//                    ElevatedButton(
//                        modifier = Modifier,
//                        onClick = {
//                            var Intent = Intent(applicationContext, Rumus::class.java)
//                            startActivity(Intent)
//                        },
//                    ) {
//                        Text(
//                            modifier = Modifier,
//                            text = "Click",
//                            color = Color.White
//                        )
//                    }
//                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyApp(
        modifier: Modifier = Modifier,
        listRumus: List<String> = listOf("Keliling persegi", "yoi")
    ) {
        Column(
            modifier = modifier
                .background(color = if (isSystemInDarkTheme()) Color.Black else Color.White),
        ) {
            TopAppBar(
                title = {
                    Text(
                        "List rumus",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
            for (name: String in listRumus) {
                Tampilan(nama = name)
            }
        }
    }



    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        name = "Dark"
    )

    @Preview()
    @Composable
    fun GreetingPreview() {
        PengolahRumusComposeTheme {
            MyApp(Modifier.fillMaxSize())
        }
    }
}



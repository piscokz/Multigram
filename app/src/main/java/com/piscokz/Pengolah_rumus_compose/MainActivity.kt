package com.piscokz.Pengolah_rumus_compose

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piscokz.Pengolah_rumus_compose.ui.theme.PengolahRumusComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PengolahRumusComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    MyApp(modifier = Modifier.fillMaxSize())
                }
            }
        }

    }
//

    @Composable
    fun Tampilan(nama: String) {

        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
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
                Column (
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(top = 25.dp),
                    horizontalAlignment = Alignment.End
                ){
                    ElevatedButton(
                        modifier = Modifier,
                        onClick = {
                            var Intent = Intent(applicationContext, Rumus::class.java)
                            startActivity(Intent)
                        },
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Click",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun MyApp(
        modifier: Modifier = Modifier,
        names: List<String> = listOf("Persegi")
    ) {
        Column(
            modifier = modifier
                .background(color = if (isSystemInDarkTheme() ) Color.Black else Color.White ),
        ) {
            for (name: String in names) {
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
            MyApp(modifier = Modifier.fillMaxSize())
        }
    }
}



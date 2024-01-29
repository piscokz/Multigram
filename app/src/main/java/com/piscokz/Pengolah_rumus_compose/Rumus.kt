package com.piscokz.Pengolah_rumus_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.piscokz.Pengolah_rumus_compose.ui.theme.PengolahRumusComposeTheme
import com.piscokz.Pengolah_rumus_compose.ui.theme.PurpleGrey40

class Rumus : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            tampilan()
        }
    }
}

@Composable
fun tampilan() {
    val isi : List<String> = listOf("hallo","hi")
    PengolahRumusComposeTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.White
        ) {

        }
    }
}

@Preview
@Composable
fun prev() {
    tampilan()
}
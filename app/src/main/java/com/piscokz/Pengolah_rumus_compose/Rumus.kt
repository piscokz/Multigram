package com.piscokz.Pengolah_rumus_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.piscokz.Pengolah_rumus_compose.ui.theme.PengolahRumusComposeTheme

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
        Surface(modifier = Modifier.fillMaxSize()) {
            Text(text = isi[0])
        }
    }
}
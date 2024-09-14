package com.piscokz.Pengolah_rumus_compose.ui.Programs.HitungPecahanBiasa

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piscokz.Pengolah_rumus_compose.ui.Programs.switchColorText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PbScreen(

) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "hitung pecahan biasa")
                }
            )
        }
    ) { paddingValues ->
        PbBody(paddingValues = paddingValues)
    }
}

@Composable
fun PbBody(paddingValues: PaddingValues) {
    Column (
        modifier = Modifier.padding(paddingValues)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {

            Text(
                color = switchColorText(),
                text = "3,14",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Thin,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, switchColorText())
                    .padding(15.dp),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
            )
        }
        Row (
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            var input by remember { mutableStateOf("8") }
            Column(
                modifier = Modifier
                    .requiredWidth(120.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BasicTextField(
                    modifier = Modifier
//                    .background(Color.DarkGray)
                    ,
                    singleLine = true,
                    value = input,
                    onValueChange = {
                        input = it
                    },
                    textStyle = TextStyle.Default.copy(
                        fontSize = MaterialTheme.typography.displayMedium.fontSize,
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(modifier = Modifier
                    .size(2.dp)
                    .border(2.dp, color = Color.Black)
                    .requiredWidth(60.dp)
                )
                Text(
                    text = "1",
                    style = MaterialTheme.typography.displayMedium
                )
            }

            Column (
                modifier = Modifier
                    .height(100.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = "+",
                    style = MaterialTheme.typography.displayMedium
                )
            }

            Column(
                modifier = Modifier.requiredWidth(120.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BasicTextField(
                    modifier = Modifier
//                    .background(Color.DarkGray)
                    ,
                    singleLine = true,
                    value = input,
                    onValueChange = {
                        input = it
                    },
                    textStyle = TextStyle.Default.copy(
                        fontSize = MaterialTheme.typography.displayMedium.fontSize,
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(modifier = Modifier
                    .size(2.dp)
                    .border(2.dp, color = Color.Black)
                    .requiredWidth(60.dp)
                )
                Text(
                    text = "1",
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }

    }
}

@Preview(
    showBackground = true
)
@Composable
private fun prev() {
    PbScreen()
}
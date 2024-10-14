package com.piscokz.Pengolah_rumus_compose.ui.Programs.KonverterMataUang

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.piscokz.Pengolah_rumus_compose.ui.Programs.customSwitchColor
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightBlue


@Composable
fun KmuScreen(
    vm : KmuViewModel = viewModel()
) {
    val rates by vm.rates.collectAsState()

    LazyColumn (modifier = Modifier.padding(16.dp)) {
        item {

            rates.forEach { (currency, rate) ->
                Text("$currency : $rate")
            }


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { vm.fetchRates(
                    apiKey = "de6f795931f43e632e0f325d0e24baaf",
                    base = "USD",
                    symbols = "IDR",
                    amount = 100
                ) },
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = customSwitchColor(
                        lighMode = Color.White,
                        darkMode = Color.Black
                    ),
                    contentColor = customSwitchColor(
                        lighMode = Color.Black,
                        darkMode = Color.LightGray
                    )
                ),
                border = BorderStroke(2.dp, LightBlue)

            ) {
                Text("Fetch Rates")
            }
        }
    }

//    Column(modifier = Modifier.padding(16.dp)) {
//        Button(onClick = {
//            vm.fetchRates("de6f795931f43e632e0f325d0e24baaf", "IDR", "USD", 10)
//        }) {
//            Text("Fetch Rates")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        rates.forEach { (currency, rate) ->
//            Text("$currency: $rate")
//        }
//    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun KmuPrev() {
    KmuScreen()
}
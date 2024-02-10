package com.piscokz.Pengolah_rumus_compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

sealed class Screen (
val route : String
){
    object Home : Screen(route = "home_screen")
    object Rumus : Screen(route = "rumus_screen")
}

@Composable
fun a() {
    LazyColumn(
        content = {

        }
    )
}

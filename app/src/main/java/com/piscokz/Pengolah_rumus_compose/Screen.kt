package com.piscokz.Pengolah_rumus_compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

sealed class Screen (
val route : String
){
    data object Home : Screen(route = "home_screen")
    data object Rumus : Screen(route = "rumus_screen")

}


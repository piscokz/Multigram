package com.piscokz.Pengolah_rumus_compose

sealed class Screen (
val route : String
){
    object Home : Screen(route = "home_screen")
    object Rumus : Screen(route = "rumus_screen")
}

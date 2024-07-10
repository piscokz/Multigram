package com.piscokz.Pengolah_rumus_compose

sealed class Screen (
val route : String
){
    data object Home : Screen(route = "home_screen")
    data object Kpp : Screen(route = "kpp_screen")
    data object Lpp : Screen(route = "lp_screen")
    data object  Km : Screen(route = "km_screen")

}


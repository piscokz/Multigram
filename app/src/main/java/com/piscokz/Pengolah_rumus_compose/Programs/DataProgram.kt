package com.piscokz.Pengolah_rumus_compose.Programs

import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.Screen


var ListKonversi : List<String> = listOf("Konverter Meter")
var ListRumus : List<String> = listOf("Keliling persegi panjang", "Luas persegi panjang",)
var listProgram : List<String> = ListKonversi + ListRumus


fun navProgram (
    navController: NavController,
    string: String
)  {
    when {
        listProgram[1].contains(string) -> navController.navigate(Screen.Kpp.route)
        listProgram[2].contains(string) -> navController.navigate(Screen.Lpp.route)
        listProgram[0].contains(string) -> navController.navigate(Screen.Km.route)
    }
}

fun tipeProgram (string: String) : String  {
    val tipeProgram = when {
        ListRumus.contains(string) -> "Rumus"
        ListKonversi.contains(string) -> "Konversi"
        else -> {"custom"}
    }
    return tipeProgram
}




package com.piscokz.Pengolah_rumus_compose.Programs

import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.Screen


var listKonversi : List<String> = listOf()
var listRumus : List<String> = listOf("keliling persegi panjang", "luas persegi panjang", "volume persegi panjang")
var listProgram : List<String> = listKonversi + listRumus


fun navProgram (
    navController: NavController,
    string: String
)  {
    when {
        listRumus[0].contains(string) -> navController.navigate(Screen.Rumus.route)
        else -> {}
    }
}
fun tipeProgram (string: String) : String  {
    var tipeProgram = when {
        listRumus.contains(string) -> "Rumus"
        listKonversi.contains(string) -> "Konversi"
        else -> {"custom"}
    }
    return tipeProgram
}




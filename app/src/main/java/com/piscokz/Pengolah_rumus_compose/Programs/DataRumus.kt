package com.piscokz.Pengolah_rumus_compose.Programs

import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.Screen


var listKonversi : List<String> = listOf("Panjang (m)", "Berat (g)")
var listRumus : List<String> = listOf("persamaan dua variabel")
var listProgram : List<String> = listRumus + listKonversi


fun navProgram (
    navController: NavController,
    string: String
)  {
    when {
        listRumus.contains(string) -> navController.navigate(Screen.Rumus.route)
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




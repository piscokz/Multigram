package com.piscokz.Pengolah_rumus_compose.Programs

import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.Screen


var listKonversi : List<String> = listOf("Ukuran panjang")
var listRumus : List<String> = listOf("Keliling persegi panjang", "Luas persegi panjang",)
var listProgram : List<String> = listKonversi + listRumus


fun navProgram (
    navController: NavController,
    string: String
)  {
    when {
        listProgram[1].contains(string) -> navController.navigate(Screen.Kpp.route)
        listProgram[2].contains(string) -> navController.navigate(Screen.Lpp.route)
        listProgram[0].contains(string) -> navController.navigate(Screen.UkuranPanjang.route)
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




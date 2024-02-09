package com.piscokz.Pengolah_rumus_compose.Programs

import androidx.navigation.NavController
import com.piscokz.Pengolah_rumus_compose.Screen

var listProgram: List<String> = listOf("Panjang (meter)", "Berat (gram)", "persamaan dua variabel")

fun navProgram (
    navController: NavController,
    string: String
)  {
    when (string) {
        listProgram[listRumusPersamaanDuaVariabel()] -> navController.navigate(Screen.Rumus.route)
        else -> ""
    }
}
fun tipeProgram (int: Int) : String  {
    var tipeProgram = when {
        listProgram[int] == "persamaan dua variabel" -> "Rumus"
        listProgram[int] == "Panjang (meter)" || listProgram[int] == "Berat (gram)" -> "Konversi"
        else -> {"custom"}
    }
    return tipeProgram
}

fun listRumusPersamaanDuaVariabel(): Int {
    return listProgram.indexOf("persamaan dua variabel")
}


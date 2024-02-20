package com.piscokz.Pengolah_rumus_compose.Programs

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.piscokz.Pengolah_rumus_compose.ui.theme.DarkButtonColors
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightButtonColors
import kotlin.math.pow

fun kpp(panjang: String, lebar: String, ukuranPanjang: String): String {
    return "Kpp = " + (2 * (panjang.toDouble() + lebar.toDouble())).toString() + " $ukuranPanjang"
}

fun lp (panjang: String, lebar: String, ukuranPanjang: String) : String {
    return "lp = " + (panjang.toDouble() * lebar.toDouble()) + " $ukuranPanjang"
}

fun konversiUkuranPanjang(
    ukuranPanjang: String,
    ukuranKonversi: String,
    nilai: String,
): String {
//    val listUkuranPanjang: List<String> = listOf("mm", "cm", "dm", "m", "dam", "hm", "km")
    var hasilNilai: Double = nilai.toDouble()
    val panjang : Int
    val konversi : Int
    val output : Int

    if (ukuranPanjang != ukuranKonversi) {
        panjang = listUkuranPanjang.indexOf(ukuranPanjang) + 1
        konversi = listUkuranPanjang.indexOf(ukuranKonversi) + 1

        if (panjang > konversi) {
            output = panjang - konversi
            hasilNilai *= 10.0.pow(output.toDouble())
        }
        else {
            output = konversi - panjang
            hasilNilai /= 10.0.pow(output.toDouble())
        }
    }
    return "$hasilNilai"
}

fun displayKpp(
    display2 : String,
    panjang : String,
    lebar : String,
    ukuranInputPanjang: String,
    ukuranInputLebar : String
): String {
    return when {
        display2.isNotEmpty() && panjang.isEmpty() && lebar.isEmpty() -> display2
        panjang.isNotEmpty() && lebar.isEmpty() -> "Kpp = 2 * ( $panjang $ukuranInputPanjang + lebar )"
        lebar.isNotEmpty() && panjang.isEmpty() -> "Kpp = 2 * ( panjang + $lebar $ukuranInputLebar )"
        panjang.isNotEmpty() && lebar.isNotEmpty() -> "Kpp = 2 * ( $panjang $ukuranInputPanjang + $lebar $ukuranInputLebar )"

        else -> {
            "Kpp = 2 * ( panjang + lebar )"
        }
    }
}

fun displaylp(
    display2 : String,
    panjang : String,
    lebar : String,
    ukuranInputPanjang: String,
    ukuranInputLebar : String
): String {
    return when {
        display2.isNotEmpty() && panjang.isEmpty() && lebar.isEmpty() -> display2
        panjang.isNotEmpty() && lebar.isEmpty() -> "lp = $panjang $ukuranInputPanjang x lebar"
        lebar.isNotEmpty() && panjang.isEmpty() -> "lp = panjang x $lebar $ukuranInputLebar"
        panjang.isNotEmpty() && lebar.isNotEmpty() -> "lp = $panjang $ukuranInputPanjang x $lebar $ukuranInputLebar"

        else -> {
            "lp = panjang x lebar"
        }
    }
}

fun displayUkuranPanjang(
    display2 : String,
    panjang : String,
    ukuranInput: String,
): String {
    return when {
        display2.isNotEmpty() && panjang.isEmpty() -> display2
        panjang.isNotEmpty() -> "$panjang $ukuranInput"
        else -> {
            "konversi"
        }
    }
}

fun cekInput(
    input1:String,
    input2: String = "uhuy"
) : Boolean {
    val hasilFinal : Boolean
    val hasil1 : String = when {
        input1.contains("-") || input1.contains(".") || input1.contains(",") -> {"false"}
        else -> {"true"}
    }
    val hasil2 : String = when {
        input2.contains("-") || input2.contains(".") || input2.contains(",") -> {"false"}
        else -> {"true"}
    }

    if (hasil1 == "true" && hasil2 == "true") { hasilFinal = true }
    else if (hasil1 == "true" && input2 == "uhuy") { hasilFinal = true }
    else { hasilFinal = false }

    return hasilFinal
}

@Composable
fun switchColor() : Color {
    var a : Color
    if (isSystemInDarkTheme()) {
        a = Color.LightGray
    }
    else {
        a = Color.Black
    }
    return a
}

@Composable
fun switchButtonColors() : ButtonColors {
    var a : ButtonColors
    if (isSystemInDarkTheme()) {
        a = ButtonColors(
            containerColor = DarkButtonColors, // background color
            contentColor = Color.White, // text color
            disabledContainerColor = Color.White,
            disabledContentColor = Color.Black
        )
    }
    else {
        a = ButtonColors(
            containerColor = LightButtonColors,
            contentColor = Color.Black,
            disabledContainerColor = Color.Black,
            disabledContentColor = Color.Red
        )
    }
    return a
}
package com.piscokz.Pengolah_rumus_compose.Programs

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.piscokz.Pengolah_rumus_compose.Programs.RumusKelilingPersegiPanjang.listUkuranPanjang
import com.piscokz.Pengolah_rumus_compose.ui.theme.DarkButtonColors
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightButtonColors
import kotlin.math.pow

//fun konversiUkuranPanjang(
//    ukuranPanjang: String,
//    ukuranKonversi: String,
//    nilai: String,
//    ): String {
//
//    var hasil: Double = nilai.toDouble()
//    val panjang: Int
//    val konversi: Int
//    val output: Double
//
//    if (ukuranPanjang != ukuranKonversi) {
////    val listUkuranPanjang: List<String> = listOf("mm", "cm", "dm", "m", "dam", "hm", "km")
//        panjang = listUkuranPanjang.indexOf(ukuranPanjang) + 1
//        konversi = listUkuranPanjang.indexOf(ukuranKonversi) + 1
//
//        if (panjang > konversi) {
//            output = (panjang - konversi).toDouble()
//            hasil *= 10.0.pow(output)
//        } else {
//            output = (konversi - panjang).toDouble()
//            hasil /= 10.0.pow(output)
//        }
//    }
//
//    return "$hasil"
//}


fun konverterUkuranPanjang(
    konversikanUkuranPanjangParam : String,
    ukuranPanjangSaatIniParam : String,
    nilai: String
) : String{
//    val listUkuranPanjang: List<String> = listOf("mm", "cm", "dm", "m", "dam", "hm", "km")
    val ukuranPanjangSaatIni : Int = listUkuranPanjang.indexOf(ukuranPanjangSaatIniParam) + 1
    val konversikanUkuranPanjang : Int = listUkuranPanjang.indexOf(konversikanUkuranPanjangParam) + 1
    var hasil : Double = nilai.toDouble()

    if (ukuranPanjangSaatIni > konversikanUkuranPanjang) {
        hasil = nilai.toDouble() * 10.0.pow(ukuranPanjangSaatIni - konversikanUkuranPanjang)
    }
    else if (ukuranPanjangSaatIni < konversikanUkuranPanjang) {
        hasil = nilai.toDouble() / 10.0.pow(konversikanUkuranPanjang - ukuranPanjangSaatIni)
    }
    return "$hasil"
}

//fun cekBilanganBulat(param : Double) {
//    if (param.toString().contains(".0")) {
//        param.roundToInt()
//    }
//}


//fun displayUkuranPanjang(
//    display2: String,
//    panjang: String,
//    ukuranInput: String,
//): String {
//    return when {
//        display2.isNotEmpty() && panjang.isEmpty() -> display2
//        panjang.isNotEmpty() -> "$panjang $ukuranInput"
//        else -> {
//            "konversi"
//        }
//    }
//}

fun cekInput(
    input1: String,
    input2: String
): Boolean {
    val hasilFinal: Boolean
    val hasil1: String = when {
        input1.contains("-") || input1.contains(",") || input1.contains(".") -> {
            "false"
        }

        else -> {
            "true"
        }
    }
    val hasil2: String = when {
        input2.contains("-") || input2.contains(",") || input2.contains(".")-> {
            "false"
        }

        else -> {
            "true"
        }
    }

    if (hasil1 == "true" && hasil2 == "true") {
        hasilFinal = true
    } else if (hasil1 == "true" && input2 == "uhuy") {
        hasilFinal = true
    } else {
        hasilFinal = false
    }

    return hasilFinal
}

@Composable
fun switchColorText(): Color {
    var a: Color
    if (isSystemInDarkTheme()) {
        a = Color.LightGray
    } else {
        a = Color.Black
    }
    return a
}

@Composable
fun switchColorTextWithBackground(): Color {
    var a: Color
    if (isSystemInDarkTheme()) {
        a = Color.Black
    } else {
        a = Color.LightGray
    }
    return a
}

@Composable
fun switchButtonColors(): ButtonColors {
    var a: ButtonColors
    if (isSystemInDarkTheme()) {
        a = ButtonColors(
            containerColor = DarkButtonColors, // background color
            contentColor = Color.White, // text color
            disabledContainerColor = Color.White,
            disabledContentColor = Color.Black
        )
    } else {
        a = ButtonColors(
            containerColor = LightButtonColors,
            contentColor = Color.Black,
            disabledContainerColor = Color.Black,
            disabledContentColor = Color.Red
        )
    }
    return a
}
package com.piscokz.Pengolah_rumus_compose.Programs

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.piscokz.Pengolah_rumus_compose.Programs.RumusKelilingPersegiPanjang.listUkuranPanjang
import com.piscokz.Pengolah_rumus_compose.ui.theme.DarkButtonColor
import com.piscokz.Pengolah_rumus_compose.ui.theme.LightButtonColors
import java.math.BigDecimal
import kotlin.math.pow
import kotlin.math.roundToLong

fun isLenghtToMuch(string: String): Boolean {
    val maxLenght = 19
    return if (string.contains("E")) {
        if (string.substringAfter("E").toInt() > maxLenght) {
            return true
        } else false
    } else return false
}

fun numberSpacing(number: String): String {
    if (!number.contains('.')) {
        val formattedNumber = number.reversed().chunked(3).joinToString(",")
        return formattedNumber.reversed()
    } else {
        val finalNumber = number.substringBefore(".")
        val formattedNumber = finalNumber.reversed().chunked(3).joinToString(",")
        return formattedNumber.reversed() + "." + number.substringAfter(".")
    }
}

fun notasiIlmiahKonverter(
    input: String,
    returnFrom_isLenghtToMuch: Boolean
): String {
    var finalResult: String = input
    if (!returnFrom_isLenghtToMuch) {
//        untuk menghilangkan akhiran 0 pada bigDecimal
        if (finalResult[finalResult.lastIndex] == '0' && finalResult.contains(".") && !finalResult.contains("E")
        ) {

            finalResult = finalResult.substring(startIndex = 0, endIndex = finalResult.lastIndex)
            if (finalResult[finalResult.lastIndex] == '.') {
                finalResult = finalResult.substring(startIndex = 0, endIndex = finalResult.lastIndex)
            }
        }
        //    untuk E-
        else if (finalResult.contains("E-")) {
            val lenghtOf0 = finalResult.substringAfter("E-").toInt()
            var resultLenghtOf0 = "0."
            var i = 0
            while (i < lenghtOf0 - 1) {
                resultLenghtOf0 += "0"; i++
            }
            finalResult = resultLenghtOf0 + finalResult.substringBefore("E").replace(".", "")
            if (finalResult[finalResult.lastIndex].toString() == "0") {
                finalResult = finalResult.substring(startIndex = 0, endIndex = finalResult.lastIndex)
            }
        }
//    untuk E+
        else if (finalResult.contains("E+")) {
            val lenghtOf0 = finalResult.substringAfter("E").toLong()
            var resultLenghtOf0 = ""
            var i = 0
            while (i < lenghtOf0) {
                resultLenghtOf0 += "0"; i++
            }
            if (finalResult.contains(".0")) {
                finalResult = finalResult.substringBefore(".0E") + resultLenghtOf0
            }
            if (finalResult.substringAfter(".") == "0") {
                finalResult =
                    finalResult.replace(".", "").replace(finalResult.substringAfter("E"), "")
                        .replace("E", "")
            } else {
                var lenghtOfNot0 = finalResult.substring(1).replace(".", "").substringBefore("E")
                resultLenghtOf0 = resultLenghtOf0.dropLast(lenghtOfNot0.length)
                finalResult = finalResult.replace(".", "").substringBefore("E") + resultLenghtOf0
            }
        }
    }
    return finalResult
}

fun isWorthItRoundToLong(number: String): String {
    if (number.contains(".")) {
        if (number.substringAfter(".") == "0") {
            return number.toDouble().roundToLong().toString()
        }
    }
    return number
}

fun hitungKelipatan(
    listNilaiDariTerkecil: List<String> = listOf("mm", "cm", "dm", "m", "dam", "hm", "km"),
    nilaiSaatIni: String = "m",
    nilaiTujuan: String = "km",
    nilaiAngkaSaatIni: Double = 100.0,
    nilaiKelipatan: Double = 10.0
): BigDecimal {
    var nilaiAkhir = nilaiAngkaSaatIni
    val kelipatan10x: Double

    if (listNilaiDariTerkecil.indexOf(nilaiSaatIni) < listNilaiDariTerkecil.indexOf(nilaiTujuan)) {

        kelipatan10x = (nilaiKelipatan).pow(
            (listNilaiDariTerkecil.indexOf(nilaiTujuan) + 1) - (listNilaiDariTerkecil.indexOf(
                nilaiSaatIni
            ) + 1)
        )
        nilaiAkhir = nilaiAngkaSaatIni / kelipatan10x
    } else if (listNilaiDariTerkecil.indexOf(nilaiSaatIni) > listNilaiDariTerkecil.indexOf(
            nilaiTujuan
        )
    ) {

        kelipatan10x = (nilaiKelipatan).pow(
            (listNilaiDariTerkecil.indexOf(nilaiSaatIni)) - (listNilaiDariTerkecil.indexOf(
                nilaiTujuan
            ))
        )
        nilaiAkhir = nilaiAngkaSaatIni * kelipatan10x
    }
    return nilaiAkhir.toBigDecimal()
//    println(listNilaiDariTerkecil.indexOf(nilaiSaatIni))
//    println(listNilaiDariTerkecil.indexOf(nilaiTujuan))
}


fun konverterUkuranPanjang(
    konversikanUkuranPanjangParam: String,
    ukuranPanjangSaatIniParam: String,
    nilai: String
): String {
//    val listUkuranPanjang: List<String> = listOf("mm", "cm", "dm", "m", "dam", "hm", "km")
    val ukuranPanjangSaatIni: Int = listUkuranPanjang.indexOf(ukuranPanjangSaatIniParam) + 1
    val konversikanUkuranPanjang: Int = listUkuranPanjang.indexOf(konversikanUkuranPanjangParam) + 1
    var hasil: Double = nilai.toDouble()

    if (ukuranPanjangSaatIni > konversikanUkuranPanjang) {
        hasil = nilai.toDouble() * 10.0.pow(ukuranPanjangSaatIni - konversikanUkuranPanjang)
    } else if (ukuranPanjangSaatIni < konversikanUkuranPanjang) {
        hasil = nilai.toDouble() / 10.0.pow(konversikanUkuranPanjang - ukuranPanjangSaatIni)
    }
    return "$hasil"
}

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
        input2.contains("-") || input2.contains(",") || input2.contains(".") -> {
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
            containerColor = DarkButtonColor, // background color
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
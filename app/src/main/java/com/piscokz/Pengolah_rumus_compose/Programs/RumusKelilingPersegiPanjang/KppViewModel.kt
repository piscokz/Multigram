package com.piscokz.Pengolah_rumus_compose.Programs.RumusKelilingPersegiPanjang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.piscokz.Pengolah_rumus_compose.Programs.konversiUkuranPanjang
import java.math.RoundingMode
import kotlin.math.roundToInt


class KppViewModel : ViewModel() {
    var panjang: String by mutableStateOf("")
    var lebar: String by mutableStateOf("")

    var inputPanjang by mutableStateOf("")
    var inputLebar by mutableStateOf("")

    var display by mutableStateOf("")

    var tekanTombolHitung by mutableStateOf(false)

    var expandedPanjang by mutableStateOf(false)
    var expandedLebar by mutableStateOf(false)
    var expandedHitung by mutableStateOf(false)

    var ukuranInputPanjang by mutableStateOf("cm")
    var ukuranInputLebar by mutableStateOf("cm")
    var ukuranInputHitung by mutableStateOf("cm")

    fun konversiUkuranKppPanjang(): String {
        return konversiUkuranPanjang(
            ukuranPanjang = ukuranInputPanjang,
            ukuranKonversi = ukuranInputHitung,
            inputPanjang
        )
    }

    fun konversiUkuranKppLebar(): String {
        return konversiUkuranPanjang(
            ukuranPanjang = ukuranInputLebar,
            ukuranKonversi = ukuranInputHitung,
            inputLebar
        )
    }

    fun displayKpp(): String {
        var returnStr: String = display
        if (display.isEmpty()) {
            returnStr = "kpp = 2 * (panjang + lebar)"
        }

        return returnStr
    }

    fun hitungKpp(): String {
        val inputPanjang: Double = this.panjang.toDouble()
        val inputLebar: Double = this.lebar.toDouble()

        val hitung: Double = 2 * (inputPanjang + inputLebar)
        var hasil: Number = hitung

        if (hasil.toString().contains(".0")) {
            hasil = hitung.roundToInt()
        }
        else {
            hasil = hitung.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
        }


        return "lpp = $hasil $ukuranInputHitung"
    }
}
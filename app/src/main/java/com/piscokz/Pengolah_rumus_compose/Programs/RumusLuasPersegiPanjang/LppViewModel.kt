package com.piscokz.Pengolah_rumus_compose.Programs.RumusLuasPersegiPanjang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.piscokz.Pengolah_rumus_compose.Programs.konverterUkuranPanjang
import kotlin.math.roundToInt

class LppViewModel : ViewModel() {
    var panjang: String by mutableStateOf("")
    var lebar: String by mutableStateOf("")

    var inputPanjang by mutableStateOf("")
    var inputLebar by mutableStateOf("")

    var display by mutableStateOf("")

    var isError by mutableStateOf(false)

    var expandedPanjang by mutableStateOf(false)
    var expandedLebar by mutableStateOf(false)
    var expandedHitung by mutableStateOf(false)

    var ukuranInputPanjang by mutableStateOf("cm")
    var ukuranInputLebar by mutableStateOf("cm")
    var ukuranInputHitung by mutableStateOf("cm")

    fun konversiUkuranLppPanjang(): String {
        var konversi = konverterUkuranPanjang(
            ukuranPanjangSaatIniParam = ukuranInputPanjang,
            konversikanUkuranPanjangParam = ukuranInputHitung,
            nilai = inputPanjang
        )
        if (konversi.contains(".0 ")) {
            konversi = konversi.toDouble().roundToInt().toString()
        }
        return konversi
    }

    fun konversiUkuranLppLebar(): String {
        return konverterUkuranPanjang(
            ukuranPanjangSaatIniParam = ukuranInputLebar,
            konversikanUkuranPanjangParam = ukuranInputHitung,
            nilai = inputLebar
        )
    }

    fun displayLpp(): String {
        var returnStr: String = display
        if (display.isEmpty()) {
            returnStr = "lp = panjang x lebar"
        }

        return returnStr
    }

    fun hitungLpp(): String {
        val inputPanjang: Double = this.panjang.toDouble()
        val inputLebar: Double = this.lebar.toDouble()

        val hitung: Double = inputPanjang * inputLebar
        var hasil: Number = hitung


//        if (hasil.toString().contains("")) {
//            hasil = hitung.roundToInt()
//        }
//        else {
            hasil = hitung.toBigDecimal()
//        }

        return "lpp = $hasil $ukuranInputHitung"
    }
}
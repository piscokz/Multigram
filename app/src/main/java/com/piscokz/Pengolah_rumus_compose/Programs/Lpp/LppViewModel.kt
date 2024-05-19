package com.piscokz.Pengolah_rumus_compose.Programs.Lpp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.piscokz.Pengolah_rumus_compose.Programs.konversiUkuranPanjang

class LppViewModel : ViewModel() {
//    var hasil : Boolean by mutableStateOf(false)
    var panjang : String by mutableStateOf("")
    var lebar : String by mutableStateOf("")

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

    fun konversiUkuranLppPanjang() : String {
        return konversiUkuranPanjang(ukuranPanjang = ukuranInputPanjang, ukuranKonversi = ukuranInputHitung, inputPanjang)
    }
    fun konversiUkuranLppLebar() : String {
        return konversiUkuranPanjang(ukuranPanjang = ukuranInputLebar, ukuranKonversi = ukuranInputHitung, inputLebar)
    }

//    fun hitungLpp2 (panjang : String, lebar : String, ukuranPanjang: String) : String {
//        return "lpp = " + (panjang.toDouble() * lebar.toDouble()) + " $ukuranPanjang"
//    }
    fun displayLpp(): String {
        return when {
            display.isNotEmpty() && panjang == inputPanjang && lebar == inputLebar -> display
            display.isNotEmpty() && inputPanjang.isEmpty() && inputLebar.isEmpty() -> display
            inputPanjang.isNotEmpty() && inputLebar.isEmpty() -> "lp = $inputPanjang $ukuranInputPanjang x lebar"
            inputLebar.isNotEmpty() && inputPanjang.isEmpty() -> "lp = panjang x $inputLebar $ukuranInputLebar"
            inputPanjang.isNotEmpty() && inputLebar.isNotEmpty() -> "lp = $inputPanjang $ukuranInputPanjang x $inputLebar $ukuranInputLebar"
            else -> {
                "lp = panjang x lebar"
            }
        }
    }
    fun hitungLpp(): String {

        val panjang : Number
        val lebar : Number
        val hasil : Number

        if (this.panjang.contains(".0") && this.lebar.contains(".0")) {
            panjang = this.panjang.toInt()
            lebar = this.lebar.toInt()
            hasil = panjang * lebar
        }
        else if (this.panjang.contains(".0")) {
            panjang = this.panjang.toInt()
            lebar = this.lebar.toDouble()
            hasil = panjang * lebar
        }
        else if (this.lebar.contains(".0")) {
            panjang = this.panjang.toDouble()
            lebar = this.lebar.toInt()
            hasil = panjang * lebar
        }
        else {
            panjang = this.panjang.toDouble()
            lebar = this.lebar.toDouble()
            hasil = panjang * lebar
        }

        return "lpp = $hasil $ukuranInputHitung"
    }
}
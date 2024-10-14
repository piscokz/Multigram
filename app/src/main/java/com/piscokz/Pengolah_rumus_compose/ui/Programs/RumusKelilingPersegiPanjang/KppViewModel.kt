package com.piscokz.Pengolah_rumus_compose.ui.Programs.RumusKelilingPersegiPanjang

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.piscokz.Pengolah_rumus_compose.ui.Programs.hitungKelipatan
import com.piscokz.Pengolah_rumus_compose.R
import java.math.RoundingMode
import kotlin.math.roundToLong


class KppViewModel : ViewModel() {
    var panjang: String by mutableStateOf("")
    var lebar: String by mutableStateOf("")

    var inputPanjang by mutableStateOf("")
    var inputLebar by mutableStateOf("")

    var display by mutableStateOf("")
    var copyDisplay by mutableStateOf("")

    var isError by mutableStateOf(false)

    var expandedPanjang by mutableStateOf(false)
    var expandedLebar by mutableStateOf(false)
    var expandedHitung by mutableStateOf(false)

    var ukuranInputPanjang by mutableStateOf("cm")
    var ukuranInputLebar by mutableStateOf("cm")
    var ukuranInputHitung by mutableStateOf("cm")

    fun konversiUkuranKppPanjang(): String {
        if (panjang != "") {
            val input: Double = this.panjang.toDouble()
            return hitungKelipatan(
                listNilaiDariTerkecil = listOf("mm", "cm", "dm", "m", "dam", "hm", "km"),
                nilaiSaatIni = ukuranInputPanjang,
                nilaiTujuan = ukuranInputHitung,
                nilaiAngkaSaatIni = input,
                nilaiKelipatan = 10.0
            ).toString()
        }
        else return ""
    }

    fun konversiUkuranKppLebar(): String {
        if (lebar != "") {
            val input: Double = this.lebar.toDouble()
            return hitungKelipatan(
                listNilaiDariTerkecil = listOf("mm", "cm", "dm", "m", "dam", "hm", "km"),
                nilaiSaatIni = ukuranInputLebar,
                nilaiTujuan = ukuranInputHitung,
                nilaiAngkaSaatIni = input,
                nilaiKelipatan = 10.0
            ).toString()
        }
        else return ""
    }

    @Composable
    fun displayKpp(): String {
        var returnStr: String = display
        if (display.isEmpty()) {
            returnStr = stringResource(id = R.string.displayKpp)
        }
        return returnStr
    }

    fun hitungKpp(): String {
        val hitung: Double = 2 * (panjang.toDouble() + lebar.toDouble())
        val hasil: Number = hitung

        copyDisplay = hasil.toString()
        return "$hasil $ukuranInputHitung"
    }
}
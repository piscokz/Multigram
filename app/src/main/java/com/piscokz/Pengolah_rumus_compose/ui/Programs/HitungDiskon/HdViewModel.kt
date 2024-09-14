package com.piscokz.Pengolah_rumus_compose.ui.Programs.HitungDiskon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HdViewModel : ViewModel() {
    var inputHarga by mutableStateOf("")
    var inputDiskon by mutableStateOf("")
    var inputDiskonSebelumnya by mutableStateOf("")
    var isAllInputNotEmpty by mutableStateOf(false)

    var harga by mutableStateOf("")
    var jumlahDiskon by mutableStateOf("")

    fun hitungDiskon() {
        val inputHarga = inputHarga.toDouble()
        val inputDiskon = inputDiskon.toDouble()

        jumlahDiskon = (inputHarga * inputDiskon / 100).toString()
        harga = (inputHarga - jumlahDiskon.toDouble()).toString()

//        harga = (inputHarga % inputDiskon).toString()
//        jumlahDiskon = (inputHarga - inputDiskon).toString()
    }
}
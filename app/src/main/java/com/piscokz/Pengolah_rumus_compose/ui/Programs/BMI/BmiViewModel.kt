package com.piscokz.Pengolah_rumus_compose.ui.Programs.BMI

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringArrayResource
import androidx.lifecycle.ViewModel
import com.piscokz.Pengolah_rumus_compose.R
import kotlin.math.pow

class BmiViewModel : ViewModel() {
    var beratBadan by mutableStateOf("")
    var tinggiBadan by mutableStateOf("")
    var hasil by mutableStateOf("")
    var statusIndeks by mutableStateOf("")

    fun hitung() {
        var konversiTinggiBadan = tinggiBadan.toDouble() / 100
        hasil = (beratBadan.toDouble() / (konversiTinggiBadan.toDouble().pow(2))).toString()
        hasil = hasil.substringBefore('.') + "." + hasil.substringAfter('.').take(2)
    }

    fun statusIndeks() {
    }
}
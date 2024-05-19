package com.piscokz.Pengolah_rumus_compose.Programs.Kpp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class KppViewModel : ViewModel() {
    var panjang : Double by mutableDoubleStateOf(0.0)
    var lebar : Double by mutableDoubleStateOf(0.0)

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
}
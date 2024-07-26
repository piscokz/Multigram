package com.piscokz.Pengolah_rumus_compose.Programs.KonverterByte

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.piscokz.Pengolah_rumus_compose.Programs.hitungKelipatan
import com.piscokz.Pengolah_rumus_compose.Programs.isLenghtToMuch
import com.piscokz.Pengolah_rumus_compose.Programs.isWorthItRoundToLong
import com.piscokz.Pengolah_rumus_compose.Programs.konverterNotasiIlmiah
import com.piscokz.Pengolah_rumus_compose.Programs.numberSpacing

class KbViewModel : ViewModel() {
    val listByte: List<String> = listOf("byte", "Kb", "Mb", "Gb", "Tb", "Pb")

    //    properties dan method untuk ui input
    var input: String by mutableStateOf("")
    var listByteCurrent: String by mutableStateOf(listByte[1])
    var expandedListByte: Boolean by mutableStateOf(false)
    var isInputError: Boolean by mutableStateOf(false)
    var inputLetterSpacing: Float by mutableFloatStateOf(1.0f)

    //    properties dan method untuk ui output
    var outputB: String by mutableStateOf("0")
    var outputKb: String by mutableStateOf("0")
    var outputMb: String by mutableStateOf("0")
    var outputGb: String by mutableStateOf("0")
    var outputTb: String by mutableStateOf("0")
    var outputPb: String by mutableStateOf("0")

    fun conversion() {
        if (this.input != "") {
            val input: Double = this.input.toDouble()
            var expanded: Boolean
            var hitung: String

//            Byte
            hitung = hitungKelipatan(
                listNilaiDariTerkecil = listByte,
                nilaiSaatIni = listByteCurrent,
                nilaiTujuan = listByte[0],
                nilaiAngkaSaatIni = input,
                nilaiKelipatan = 1000.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputB = numberSpacing(
                isWorthItRoundToLong(
                    konverterNotasiIlmiah(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            Kb
            hitung = hitungKelipatan(
                listByte,
                listByteCurrent,
                listByte[1],
                input,
                1000.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputKb = numberSpacing(
                isWorthItRoundToLong(
                    konverterNotasiIlmiah(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            Mb
            hitung = hitungKelipatan(
                listByte,
                listByteCurrent,
                listByte[2],
                input,
                1000.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputMb = numberSpacing(
                isWorthItRoundToLong(
                    konverterNotasiIlmiah(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            Gb
            hitung = hitungKelipatan(
                listByte,
                listByteCurrent,
                listByte[3],
                input,
                1000.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputGb = numberSpacing(
                isWorthItRoundToLong(
                    konverterNotasiIlmiah(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            Tb
            hitung = hitungKelipatan(
                listByte,
                listByteCurrent,
                listByte[4],
                input,
                1000.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputTb = numberSpacing(
                isWorthItRoundToLong(
                    konverterNotasiIlmiah(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            Pb
            hitung = hitungKelipatan(
                listByte,
                listByteCurrent,
                listByte[5],
                input,
                1000.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputPb = numberSpacing(
                isWorthItRoundToLong(
                    konverterNotasiIlmiah(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )
            
        } else {
            outputB = "0"
            outputKb = "0"
            outputMb = "0"
            outputGb = "0"
            outputTb = "0"
            outputPb = "0"
        }
    }
}
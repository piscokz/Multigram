package com.piscokz.Pengolah_rumus_compose.Programs.KonverterByte

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.piscokz.Pengolah_rumus_compose.Programs.hitungKelipatan
import com.piscokz.Pengolah_rumus_compose.Programs.isLenghtToMuch
import com.piscokz.Pengolah_rumus_compose.Programs.isWorthItRoundToLong
import com.piscokz.Pengolah_rumus_compose.Programs.notasiIlmiahKonverter
import com.piscokz.Pengolah_rumus_compose.Programs.numberSpacing

class KbViewModel : ViewModel() {
    val listByte: List<String> = listOf("B", "Kb", "Mb", "Gb", "Tb", "Pb")

    //    properties dan method untuk ui input
    var input: String by mutableStateOf("")
    var listByteCurrent: String by mutableStateOf("Kb")
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
                listByte,
                listByteCurrent,
                "B",
                input,
                1000.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputB = numberSpacing(
                isWorthItRoundToLong(
                    notasiIlmiahKonverter(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            KiloByte
            hitung = hitungKelipatan(
                listByte,
                listByteCurrent,
                "Kb",
                input,
                1000.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputKb = numberSpacing(
                isWorthItRoundToLong(
                    notasiIlmiahKonverter(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            MegaByte
            hitung = hitungKelipatan(
                listByte,
                listByteCurrent,
                "Mb",
                input,
                1000.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputMb = numberSpacing(
                isWorthItRoundToLong(
                    notasiIlmiahKonverter(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            GigaByte
            hitung = hitungKelipatan(
                listByte,
                listByteCurrent,
                "Gb",
                input,
                1000.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputGb = numberSpacing(
                isWorthItRoundToLong(
                    notasiIlmiahKonverter(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            TeraByte
            hitung = hitungKelipatan(
                listByte,
                listByteCurrent,
                "Tb",
                input,
                1000.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputTb = numberSpacing(
                isWorthItRoundToLong(
                    notasiIlmiahKonverter(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            PetaByte
            hitung = hitungKelipatan(
                listByte,
                listByteCurrent,
                "Pb",
                input,
                1000.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputPb = numberSpacing(
                isWorthItRoundToLong(
                    notasiIlmiahKonverter(
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
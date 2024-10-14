package com.piscokz.Pengolah_rumus_compose.ui.Programs.KonverterGram

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.piscokz.Pengolah_rumus_compose.ui.Programs.hitungKelipatan
import com.piscokz.Pengolah_rumus_compose.ui.Programs.isLenghtToMuch
import com.piscokz.Pengolah_rumus_compose.ui.Programs.isWorthItRoundToLong
import com.piscokz.Pengolah_rumus_compose.ui.Programs.konverterNotasiIlmiah
import com.piscokz.Pengolah_rumus_compose.ui.Programs.numberSpacing

class KonverterGramViewModel : ViewModel() {
    val listGram: List<String> = listOf("mg", "cg", "dg", "gram", "dag", "hg", "kg")

    //    properties dan method untuk ui input
    var input: String by mutableStateOf("")
    var listGramCurrent: String by mutableStateOf("gram")
    var expandedListGram: Boolean by mutableStateOf(false)
    var isInputError: Boolean by mutableStateOf(false)
    var inputLetterSpacing: Float by mutableFloatStateOf(1.0f)

    //    properties dan method untuk ui output
    var outputMg: String by mutableStateOf("0")
    var outputCg: String by mutableStateOf("0")
    var outputDg: String by mutableStateOf("0")
    var outputG: String by mutableStateOf("0")
    var outputDag: String by mutableStateOf("0")
    var outputHg: String by mutableStateOf("0")
    var outputKg: String by mutableStateOf("0")

    fun conversion() {
        if (this.input != "") {
            val input: Double = this.input.toDouble()
//            outputMm = hitungKelipatan(listMeter, listMeterCurrent, "mm", input, 10.0).toString()
//            outputCm = hitungKelipatan(listMeter, listMeterCurrent, "cm", input, 10.0).toString()
//            outputDm = hitungKelipatan(listMeter, listMeterCurrent, "dm", input, 10.0).toString()
//            outputM = hitungKelipatan(listMeter, listMeterCurrent, "m", input, 10.0).toString()
//            outputDam = hitungKelipatan(listMeter, listMeterCurrent, "dam", input, 10.0).toString()
//            outputHm = hitungKelipatan(listMeter, listMeterCurrent, "hm", input, 10.0).toString()
//            outputKm = hitungKelipatan(listMeter, listMeterCurrent, "km", input, 10.0).toString()
            var hitung: String
            var expanded: Boolean

//            mg
            hitung = hitungKelipatan(
                listGram,
                listGramCurrent,
                listGram[0],
                input,
                10.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputMg = numberSpacing(
                isWorthItRoundToLong(
                    konverterNotasiIlmiah(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            cg
            hitung = hitungKelipatan(
                listGram,
                listGramCurrent,
                listGram[1],
                input,
                10.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputCg = numberSpacing(
                isWorthItRoundToLong(
                    konverterNotasiIlmiah(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            dg
            hitung = hitungKelipatan(
                listGram,
                listGramCurrent,
                listGram[2],
                input,
                10.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputDg = numberSpacing(
                isWorthItRoundToLong(
                    konverterNotasiIlmiah(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            gram
            hitung = hitungKelipatan(
                listGram,
                listGramCurrent,
                listGram[3],
                input,
                10.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputG = numberSpacing(
                isWorthItRoundToLong(
                    konverterNotasiIlmiah(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )
//            dag
            hitung = hitungKelipatan(
                listGram,
                listGramCurrent,
                listGram[4],
                input,
                10.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputDag = numberSpacing(
                isWorthItRoundToLong(
                    konverterNotasiIlmiah(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )
//            hg
            hitung = hitungKelipatan(
                listGram,
                listGramCurrent,
                listGram[5],
                input,
                10.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputHg = numberSpacing(
                isWorthItRoundToLong(
                    konverterNotasiIlmiah(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )
//            kg
            hitung = hitungKelipatan(
                listGram,
                listGramCurrent,
                listGram[6],
                input,
                10.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputKg = numberSpacing(
                isWorthItRoundToLong(
                    konverterNotasiIlmiah(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )
        } else {
            outputMg = "0"
            outputCg = "0"
            outputDg = "0"
            outputG = "0"
            outputDag = "0"
            outputHg = "0"
            outputKg = "0"
        }
    }
}
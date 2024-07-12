package com.piscokz.Pengolah_rumus_compose.Programs.KonverterMeter

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

class KmViewModel : ViewModel() {
    val listMeter: List<String> = listOf("mm", "cm", "dm", "m", "dam", "hm", "km")

    //    properties dan method untuk ui input
    var input: String by mutableStateOf("")
    var listMeterCurrent: String by mutableStateOf("cm")
    var expandedListMeter: Boolean by mutableStateOf(false)
    var isInputError: Boolean by mutableStateOf(false)
    var inputLetterSpacing: Float by mutableFloatStateOf(1.0f)

    //    properties dan method untuk ui output
    var outputMm: String by mutableStateOf("0")
    var outputCm: String by mutableStateOf("0")
    var outputDm: String by mutableStateOf("0")
    var outputM: String by mutableStateOf("0")
    var outputDam: String by mutableStateOf("0")
    var outputHm: String by mutableStateOf("0")
    var outputKm: String by mutableStateOf("0")

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

//            miliMeter
            hitung = hitungKelipatan(
                listMeter,
                listMeterCurrent,
                "mm",
                input,
                10.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputMm = numberSpacing(
                isWorthItRoundToLong(
                    notasiIlmiahKonverter(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            centiMeter
            hitung = hitungKelipatan(
                listMeter,
                listMeterCurrent,
                "cm",
                input,
                10.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputCm = numberSpacing(
                isWorthItRoundToLong(
                    notasiIlmiahKonverter(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            desiMeter
            hitung = hitungKelipatan(
                listMeter,
                listMeterCurrent,
                "dm",
                input,
                10.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputDm = numberSpacing(
                isWorthItRoundToLong(
                    notasiIlmiahKonverter(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )

//            meter
            hitung = hitungKelipatan(
                listMeter,
                listMeterCurrent,
                "m",
                input,
                10.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputM = numberSpacing(
                isWorthItRoundToLong(
                    notasiIlmiahKonverter(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )
//            dekaMeter
            hitung = hitungKelipatan(
                listMeter,
                listMeterCurrent,
                "dam",
                input,
                10.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputDam = numberSpacing(
                isWorthItRoundToLong(
                    notasiIlmiahKonverter(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )
//            hektoMeter
            hitung = hitungKelipatan(
                listMeter,
                listMeterCurrent,
                "hm",
                input,
                10.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputHm = numberSpacing(
                isWorthItRoundToLong(
                    notasiIlmiahKonverter(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )
//            kiloMeter
            hitung = hitungKelipatan(
                listMeter,
                listMeterCurrent,
                "km",
                input,
                10.0
            ).toString()
            expanded = isLenghtToMuch(hitung)
            outputKm = numberSpacing(
                isWorthItRoundToLong(
                    notasiIlmiahKonverter(
                        input = hitung,
                        returnFrom_isLenghtToMuch = expanded
                    )
                )
            )
        } else {
            outputMm = "0"
            outputCm = "0"
            outputDm = "0"
            outputM = "0"
            outputDam = "0"
            outputHm = "0"
            outputKm = "0"
        }
    }
}
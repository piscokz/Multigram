package com.piscokz.Pengolah_rumus_compose.Programs.KonverterPanjang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.roundToLong

class KpViewModel : ViewModel() {
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

    fun numberSpacing(number: String): String {
        if (!number.contains('.')) {
            val formattedNumber = number.reversed().chunked(3).joinToString(",")
            return formattedNumber.reversed()
        } else {
            val finalNumber = number.substringBefore(".")
            val formattedNumber = finalNumber.reversed().chunked(3).joinToString(",")
            return formattedNumber.reversed() + "." + number.substringAfter(".")
        }
    }

    fun notasiIlmiahKonverter(input: String): String {
        var finalResult: String = input
        if (finalResult.contains("E-")) {
            val lenghtOf0 = input.toString().substringAfter("E-").toInt()
            var resultLenghtOf0 = "0."
            var i = 0
            while (i < lenghtOf0 - 1) {
                resultLenghtOf0 += "0"; i++
            }

            if (finalResult.contains(".0")) {
                finalResult = resultLenghtOf0 + finalResult.substringBefore(".0E")
            } else {
                finalResult = resultLenghtOf0 + finalResult.replace(".", "").substringBefore("E")
            }
        }
//    untuk E- sudah, tinggal E+
        else if (input.contains("E+")) {
            val lenghtOf0 = input.toString().substringAfter("E").toLong()
            var resultLenghtOf0 = ""
            var i = 0
            while (i < lenghtOf0) {
                resultLenghtOf0 += "0"; i++
            }
            if (finalResult.contains(".0")) {
                finalResult = finalResult.substringBefore(".0E") + resultLenghtOf0
            }
            if(finalResult.substringAfter(".") == "0") {
                finalResult = finalResult.replace(".", "").replace(finalResult.substringAfter("E"), "").replace("E", "")
            }
            else {
                var lenghtOfNot0 = finalResult.substring(1).replace(".", "").substringBefore("E")
                resultLenghtOf0 = resultLenghtOf0.dropLast(lenghtOfNot0.length)
                finalResult = finalResult.replace(".", "").substringBefore("E") + resultLenghtOf0
//                finalResult = finalResult.replace(".","").substringBefore("E") + resultLenghtOf0
            }
        }
        return finalResult
    }

    fun isWorthItRoundToLong(number: String): String {
        if (number.contains(".")) {
            if (number.substringAfter(".") == "0") {
                return number.toDouble().roundToLong().toString()
            }
        }
        return number
    }

    fun conversion() {
        if (this.input != "") {
            val input: Double = this.input.toDouble()
            when (listMeterCurrent) {
                "mm" -> {
                    outputMm = input.roundToLong().toString()
                    outputCm = (input / 10).toBigDecimal().toString()
                    outputDm = (input / 100).toBigDecimal().toString()
                    outputM = (input / 1_000).toBigDecimal().toString()
                    outputDam = (input / 10_000).toBigDecimal().toString()
                    outputHm = (input / 100_000).toBigDecimal().toString()
                    outputKm = (input / 1_000_000).toBigDecimal().toString()
                }

                "cm" -> {
                    outputMm = (input * 10.0).toBigDecimal().toString()
                    outputCm = input.toLong().toString()
                    outputDm = (input / 10.0).toBigDecimal().toString()
                    outputM = (input / 100.0).toBigDecimal().toString()
                    outputDam = (input / 1_000.0).toBigDecimal().toString()
                    outputHm = (input / 10_000.0).toBigDecimal().toString()
                    outputKm = (input / 100_000.0).toBigDecimal().toString()
                }

                "dm" -> {
                    outputMm = (input * 100.0).toBigDecimal().toString()
                    outputCm = (input * 10.0).toBigDecimal().toString()
                    outputDm = input.toLong().toString()
                    outputM = (input / 10.0).toBigDecimal().toString()
                    outputDam = (input / 100.0).toBigDecimal().toString()
                    outputHm = (input / 1_000.0).toBigDecimal().toString()
                    outputKm = (input / 10_000.0).toBigDecimal().toString()
                }

                "m" -> {
                    outputMm = (input * 1_000.0).toBigDecimal().toString()
                    outputCm = (input * 100.0).toBigDecimal().toString()
                    outputDm = (input * 10.0).toBigDecimal().toString()
                    outputM = input.toLong().toString()
                    outputDam = (input / 10.0).toBigDecimal().toString()
                    outputHm = (input / 100.0).toBigDecimal().toString()
                    outputKm = (input / 1_000.0).toBigDecimal().toString()
                }

                "dam" -> {
                    outputMm = (input * 10_000.0).toBigDecimal().toString()
                    outputCm = (input * 1_000.0).toBigDecimal().toString()
                    outputDm = (input * 100.0).toBigDecimal().toString()
                    outputM = (input * 10.0).toBigDecimal().toString()
                    outputDam = input.toLong().toString()
                    outputHm = (input / 10.0).toBigDecimal().toString()
                    outputKm = (input / 100.0).toBigDecimal().toString()
                }

                "hm" -> {
                    outputMm = (input * 100_000.0).toBigDecimal().toString()
                    outputCm = (input * 10_000.0).toBigDecimal().toString()
                    outputDm = (input * 1_000.0).toBigDecimal().toString()
                    outputM = (input * 100.0).toBigDecimal().toString()
                    outputDam = (input * 10.0).toBigDecimal().toString()
                    outputHm = input.toLong().toString()
                    outputKm = (input / 10.0).toBigDecimal().toString()
                }

                "km" -> {
                    outputMm = (input * 1_000_000.0).toBigDecimal().toString()
                    outputCm = (input * 100_000.0).toBigDecimal().toString()
                    outputDm = (input * 10_000.0).toBigDecimal().toString()
                    outputM = (input * 1_000.0).toBigDecimal().toString()
                    outputDam = (input * 100.0).toBigDecimal().toString()
                    outputHm = (input * 10.0).toBigDecimal().toString()
                    outputKm = input.toBigDecimal().toString()
                }
            }
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
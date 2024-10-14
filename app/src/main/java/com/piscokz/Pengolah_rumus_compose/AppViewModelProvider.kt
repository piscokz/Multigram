package com.piscokz.Pengolah_rumus_compose

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.piscokz.Pengolah_rumus_compose.ui.Programs.BMI.BmiViewModel
import com.piscokz.Pengolah_rumus_compose.ui.Programs.Calculator.CalculatorViewModel
import com.piscokz.Pengolah_rumus_compose.ui.Programs.HitungDiskon.HdViewModel
import com.piscokz.Pengolah_rumus_compose.ui.Programs.KonverterByte.KbViewModel
import com.piscokz.Pengolah_rumus_compose.ui.Programs.KonverterGram.KonverterGramViewModel
import com.piscokz.Pengolah_rumus_compose.ui.Programs.KonverterMeter.KmViewModel
import com.piscokz.Pengolah_rumus_compose.ui.Programs.RumusKelilingPersegiPanjang.KppViewModel
import com.piscokz.Pengolah_rumus_compose.ui.Programs.RumusLuasPersegiPanjang.LppViewModel
import com.piscokz.Pengolah_rumus_compose.ui.Programs.RumusPersegi.PersegiViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer { KppViewModel() }
        initializer { LppViewModel() }
        initializer { KmViewModel() }
        initializer { KbViewModel() }
        initializer { HdViewModel() }
        initializer { CalculatorViewModel() }
        initializer { BmiViewModel() }
        initializer { KonverterGramViewModel() }
        initializer { PersegiViewModel() }
    }
}
package com.piscokz.Pengolah_rumus_compose

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.piscokz.Pengolah_rumus_compose.Programs.RumusKelilingPersegiPanjang.KppViewModel
import com.piscokz.Pengolah_rumus_compose.Programs.RumusLuasPersegiPanjang.LppViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            KppViewModel()
        }
        initializer {
            LppViewModel()
        }
    }
}
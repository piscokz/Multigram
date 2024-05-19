package com.piscokz.Pengolah_rumus_compose

import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.piscokz.Pengolah_rumus_compose.Programs.Kpp.KppViewModel
import com.piscokz.Pengolah_rumus_compose.Programs.Lpp.LppViewModel

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
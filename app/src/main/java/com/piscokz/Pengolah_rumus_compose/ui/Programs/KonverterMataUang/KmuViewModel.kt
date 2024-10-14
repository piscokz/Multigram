package com.piscokz.Pengolah_rumus_compose.ui.Programs.KonverterMataUang


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class KmuViewModel : ViewModel() {
    private val _rates = MutableStateFlow<Map<String, Double>>(emptyMap())
    val rates: StateFlow<Map<String, Double>> get() = _rates

    fun fetchRates(
        apiKey: String,
        base: String,
        symbols: String,
        amount : Int
    ) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getLatestRates(
                    apiKey,
                    base,
                    symbols,
                    amount
                )
                _rates.value = response.rates
            } catch (e: Exception) {
                Log.e("ExchangeRatesViewModel", "Error fetching rates", e)
            }
        }
    }
}
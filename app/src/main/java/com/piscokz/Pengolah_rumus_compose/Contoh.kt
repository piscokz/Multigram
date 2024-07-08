package com.piscokz.Pengolah_rumus_compose

import android.content.res.Configuration
import android.content.Context
import android.view.WindowManager

class OrientationChecker(private val context: Context) {
    fun getOrientation(): String {
        val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val rotation = display.rotation

        return when (rotation) {
            0, 2 -> "Portrait" // Portrait or reverse portrait
            1, 3 -> "Landscape" // Landscape or reverse landscape
            else -> "Unknown"
        }
    }
}

// Contoh penggunaan:
//val orientationChecker = OrientationChecker(context)
//val currentOrientation = orientationChecker.getOrientation()
//println("Orientasi perangkat saat ini: $currentOrientation")
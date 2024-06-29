package com.piscokz.Pengolah_rumus_compose.Programs.RumusKelilingPersegiPanjang

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun contoh(modifier: Modifier = Modifier) {
    val focusRequester = remember { FocusRequester() }
    var value by remember { mutableStateOf("apple") }
    var borderColor by remember { mutableStateOf(Transparent) }
    TextField(
        enabled = true,
        value = value,
        onValueChange = {
            value = it.apply {
                if (length > 5) focusRequester.captureFocus() else focusRequester.freeFocus()
            }
        },
        modifier = Modifier
            .border(2.dp, borderColor)
            .focusRequester(focusRequester)
            .onFocusChanged { borderColor = if (it.isCaptured) Red else Transparent }
    )
}

@Preview(showBackground = true)
@Composable
private fun coba() {
    contoh(modifier = Modifier.background(White))
}
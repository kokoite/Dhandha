package com.example.dhandha.helper

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object NoRippleTheme: RippleTheme {
    @Composable
    override fun defaultColor(): Color {
        return Color.White
    }

    @Composable
    override fun rippleAlpha(): RippleAlpha {
        return RippleAlpha(draggedAlpha = 0f, hoveredAlpha = 0f, focusedAlpha = 0f, pressedAlpha = 0f)
    }
}
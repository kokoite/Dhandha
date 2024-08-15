package com.example.dhandha.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// colors

data class AppColorScheme(val background: Color,
                          val primaryColor: Color,
                          val secondaryColor: Color )

// typography

data class AppTypography(
    val headerTitle: TextStyle,
    val containerTitle: TextStyle,
    val label: TextStyle,
    val placeholder: TextStyle
)

// shape



data class AppShape(val container: Shape,
                    val header: Shape,
                    val button: Shape)

// size

data class AppImageSize(val small: Dp, val medium: Dp, val large: Dp)

data class AppSpacing(val container: Dp, val inset: Dp)


val LocalAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(background = Color.Unspecified, primaryColor = Color.Unspecified, secondaryColor = Color.Unspecified)
}


val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        headerTitle = TextStyle.Default,
        containerTitle = TextStyle.Default,
        label = TextStyle.Default,
        placeholder = TextStyle.Default
        )
}

val LocalAppSpacing = staticCompositionLocalOf {
    AppSpacing(container = Dp.Unspecified, inset = Dp.Unspecified)
}
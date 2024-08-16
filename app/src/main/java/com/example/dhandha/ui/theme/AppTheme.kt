package com.example.dhandha.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.dhandha.R

private val DarkColorScheme = AppColorScheme(
    background = Color.Black,
    primaryColor = Color.White,
    secondaryColor = Color.White
)

private val LightColorScheme = AppColorScheme(
    background = Color(red = 246, green = 246, blue = 255),
    primaryColor = Color.White,
    secondaryColor = Color.White
)

private val Roboto = FontFamily(
    Font(R.font.roboto_black, FontWeight.Black),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_thin, FontWeight.Thin),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_regular, FontWeight.Normal)
)

private val appTypography = AppTypography(
    headerTitle = TextStyle(fontSize = 24.sp, fontFamily = Roboto, fontWeight = FontWeight.Bold ),
    containerTitle = TextStyle(fontSize = 16.sp, fontFamily = Roboto, fontWeight = FontWeight.SemiBold, color = Color.LightGray),
    label = TextStyle(fontSize = 16.sp, fontFamily = Roboto, fontWeight = FontWeight.SemiBold),
    placeholder =  TextStyle(fontSize = 14.sp, fontFamily = Roboto, fontWeight = FontWeight.Normal, color = Color.Gray),
    buttonText = TextStyle(fontSize = 15.sp, fontFamily = Roboto, fontWeight = FontWeight.Normal)
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color(red = 246, green = 246, blue = 255).toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    val colorScheme = if(darkTheme) {
        LightColorScheme
    } else {
        LightColorScheme
    }

    CompositionLocalProvider(
        LocalAppTypography provides appTypography,
        LocalAppColorScheme provides colorScheme
        ) {
        content()
    }
}


object AppTheme {

    val colorScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current


    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

}

package com.yusuf.personaltrainer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    accentColor: Color = NeonBlue,
    content: @Composable () -> Unit
) {

    val DarkColorScheme = darkColorScheme(
        primary = accentColor,
        secondary = accentColor,
        tertiary = accentColor,

        background = GymBlack,
        surface = GymCard,

        onPrimary = Color.Black,
        onSecondary = Color.Black,
        onBackground = Color.White,
        onSurface = Color.White
    )

    val LightColorScheme = lightColorScheme(
        primary = accentColor,
        secondary = accentColor,
        tertiary = accentColor,

        background = Color.White,
        surface = Color(0xFFF5F5F5),

        onPrimary = Color.White,
        onSecondary = Color.White,
        onBackground = Color.Black,
        onSurface = Color.Black
    )

    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}
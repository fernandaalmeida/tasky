package com.falmeida.tasky.designsystem.component.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val DarkColorScheme = darkColorScheme(
    primary = TaskyGreen,
    background = TaskyBlack,
    surface = TaskyGraySurface,
    secondary = TaskyWhite,
    tertiary = TaskyLime,
    primaryContainer = TaskyGreen30,
    onPrimary = TaskyBlack,
    onBackground = TaskyWhite,
    onSurface = TaskyWhite,
)

val LightColorScheme = lightColorScheme(
    primary = TaskyGreen,
    background = TaskyWhite,
    surface = TaskyGraySurfaceLight,
    secondary = TaskyBlack,
    tertiary = TaskyLime,
    primaryContainer = TaskyGreen10,
    onPrimary = TaskyWhite,
    onBackground = TaskyBlack,
    onSurface = TaskyBlack,
)

@Composable
fun TaskyTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (useDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TaskyTypography,
        content = content
    )
}
package com.falmeida.tasky.designsystem.component.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = PrimaryLight,
    secondary = Secondary,
    surface = SurfaceLight,
    background = SurfaceHigherLight,
    tertiary = Tertiary
)

private val DarkColors = darkColorScheme(
    primary = PrimaryDark,
    secondary = Secondary,
    surface = SurfaceDark,
    background = SurfaceHigherDark,
    tertiary = Tertiary
)

@Composable
fun TaskyTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (useDarkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TaskyTypography,
        content = content
    )
}
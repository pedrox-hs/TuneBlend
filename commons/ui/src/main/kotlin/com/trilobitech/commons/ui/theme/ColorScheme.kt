package com.trilobitech.commons.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val lightColors = lightColorScheme(
    primary = LightBlue,
    secondary = DeepOrangeA200,
    tertiary = Purple500,
    surface = WhiteAlpha70,
)

private val darkColors = darkColorScheme(
    primary = SkyBlue,
    secondary = CoralPink,
    tertiary = LilacPurple,
    surface = BlackAlpha70,
)

@Composable
internal fun colorScheme(useDarkTheme: Boolean): ColorScheme = when {
    useDarkTheme -> darkColors
    else -> lightColors
}

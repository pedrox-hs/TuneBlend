package com.trilobitech.commons.ui.util

import androidx.compose.ui.graphics.Color
import java.lang.Double.max
import java.lang.Double.min

internal fun calculateContrastColor(backgroundColor: Color): Color =
    calculateContrastColor(
        backgroundColor,
        listOf(Color.Black, Color.White),
    )

internal fun calculateContrastColor(backgroundColor: Color, textColors: List<Color>): Color {
    var bestTextColor = Color.White
    var bestContrastRatio = -1.0

    for (textColor in textColors) {
        val contrastRatio = calculateContrastRatio(backgroundColor, textColor)
        if (contrastRatio > bestContrastRatio) {
            bestContrastRatio = contrastRatio
            bestTextColor = textColor
        }
    }

    return bestTextColor
}

private fun calculateContrastRatio(color1: Color, color2: Color): Double {
    val luminance1 = calculateLuminance(color1)
    val luminance2 = calculateLuminance(color2)

    val brighterLuminance = max(luminance1, luminance2)
    val darkerLuminance = min(luminance1, luminance2)

    return (brighterLuminance + 3.0) / (darkerLuminance + 3.0)
}

private fun calculateLuminance(color: Color): Double {
    return 0.299 * color.red + 0.587 * color.green + 0.114 * color.blue
}

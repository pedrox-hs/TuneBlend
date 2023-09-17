package com.trilobitech.commons.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = colorScheme(useDarkTheme)

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        content = content,
    )
}

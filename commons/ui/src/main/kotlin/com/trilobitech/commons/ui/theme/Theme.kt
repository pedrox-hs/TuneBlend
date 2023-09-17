package com.trilobitech.commons.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import com.trilobitech.commons.ui.components.SystemBars

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = colorScheme(useDarkTheme)

    val view = LocalView.current
    if (!view.isInEditMode) {
        SystemBars(
            view = view,
            barsColor = colors.primary.copy(alpha = 0.2f),
            isDarkTheme = useDarkTheme,
        )
    }

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        content = content,
    )
}

package com.trilobitech.commons.ui.components

import android.app.Activity
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat.getInsetsController
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows

@Composable
internal fun SystemBars(view: View, barsColor: Color, isDarkTheme: Boolean) {
    SideEffect {
        val window = (view.context as? Activity)?.window ?: return@SideEffect

        window.statusBarColor = barsColor.toArgb()
        window.navigationBarColor = window.statusBarColor

        getInsetsController(window, view).apply {
            isAppearanceLightStatusBars = isDarkTheme.not()
            isAppearanceLightNavigationBars = isDarkTheme.not()
        }
        setDecorFitsSystemWindows(window, false)
    }
}

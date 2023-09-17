package com.trilobitech.commons.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.trilobitech.commons.ui.util.ColorSchemeTokens
import com.trilobitech.commons.ui.util.TypographyTokens
import com.trilobitech.commons.ui.util.calculateContrastColor
import com.trilobitech.commons.ui.util.readField

@Composable
fun TypographyScreen() {
    Screen {
        TypographyTokens.forEach { typeName ->
            Typography(typeName = typeName)
        }
    }
}

@Composable
internal fun ColorSchemeScreen() {
    Screen {
        ColorSchemeTokens.forEach { colorName ->
            ColorPalette(colorName = colorName)
        }
    }
}

@Composable
internal fun Screen(content: @Composable ColumnScope.() -> Unit) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeContent,
    ) { contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(colorScheme.background),
            content = content,
        )
    }
}

@Composable
private fun Typography(
    typeName: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val style = typography.readField<TextStyle>(typeName)

        Text(
            text = typeName,
            color = colorScheme.onBackground,
            style = style,
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize(),
        )
    }
}

@Composable
private fun ColumnScope.ColorPalette(
    colorName: String,
) {
    val backgroundColor: Color = colorScheme.readField(colorName)
    val textColor = calculateContrastColor(backgroundColor)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(backgroundColor)
    ) {
        Text(
            text = colorName,
            color = textColor,
            style = typography.bodySmall.copy(fontSize = 12.sp),
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize(),
        )
    }
}

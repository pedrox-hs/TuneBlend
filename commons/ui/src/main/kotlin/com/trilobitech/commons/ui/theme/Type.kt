package com.trilobitech.commons.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.trilobitech.commons.ui.R
import com.trilobitech.commons.ui.ext.applyFontFamily

internal val typography = Typography()
    .applyFontFamily(
        fontFamily = FontFamily(
            Font(R.font.poppins_light, FontWeight.Light),
            Font(R.font.poppins_regular, FontWeight.Normal),
            Font(R.font.poppins_italic, FontWeight.Normal, FontStyle.Italic),
            Font(R.font.poppins_medium, FontWeight.Medium),
            Font(R.font.poppins_bold, FontWeight.Bold),
        ),
    )

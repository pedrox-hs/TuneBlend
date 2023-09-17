package com.trilobitech.commons.ui

import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_5
import app.cash.paparazzi.Paparazzi
import com.trilobitech.commons.ui.theme.AppTheme
import com.trilobitech.commons.ui.util.DefaultTheme
import org.junit.Rule
import org.junit.Test

class ThemeTest {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = PIXEL_5,
        theme = DefaultTheme,
    )

    @Test
    fun showCorrectTypography() {
        paparazzi.snapshot {
            AppTheme {
                TypographyScreen()
            }
        }
    }
}

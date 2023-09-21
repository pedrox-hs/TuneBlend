plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false

    alias(libs.plugins.sonarqube) apply true
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.detekt) apply false

    alias(libs.plugins.module.root)
}

group = "com.trilobitech"

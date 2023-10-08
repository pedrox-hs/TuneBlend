plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false

    alias(libs.plugins.sonarqube) apply true
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.ksp) apply false

    id(libs.plugins.module.root.get().pluginId)
}

group = "com.trilobitech"

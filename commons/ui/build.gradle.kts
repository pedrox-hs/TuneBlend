plugins {
    id(libs.plugins.module.library.get().pluginId)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.paparazzi)
}

android {
    resourcePrefix = ""

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(platform(libs.compose.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.compose.material3)

    debugImplementation(kotlin("reflect"))
}

plugins {
    id(libs.plugins.module.library.get().pluginId)
    alias(libs.plugins.paparazzi)
}

android {
    resourcePrefix = ""

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    api(platform(libs.compose.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.compose.material3)

    debugImplementation(kotlin("reflect"))
}

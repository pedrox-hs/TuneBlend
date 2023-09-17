plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
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

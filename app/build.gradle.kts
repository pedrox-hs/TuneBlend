plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.trilobitech.tuneblend"

    defaultConfig {
        applicationId = "com.trilobitech.tuneblend"

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.android.layout)

    implementation(projects.features.login.presentation.android)

    testImplementation(libs.bundles.testing.unit)

    androidTestImplementation(libs.bundles.testing.android)
}

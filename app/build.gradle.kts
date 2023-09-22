import java.util.Properties

plugins {
    id(libs.plugins.module.application.get().pluginId)
}

private val debugKeystorePath = "debug.keystore"

val keystoreProperties = Properties().apply {
    file("keystore.properties")
        .takeIf { it.exists() }
        ?.let { load(it.inputStream()) }
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file(debugKeystorePath)
        }
        create("release") {
            storeFile = file(keystoreProperties.getProperty("keystore.file", debugKeystorePath))
            storePassword = keystoreProperties.getProperty("keystore.password")
            keyAlias = keystoreProperties.getProperty("keystore.alias")
            keyPassword = keystoreProperties.getProperty("keystore.password")
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
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
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.compose.activity)
    implementation(projects.commons.ui)
}

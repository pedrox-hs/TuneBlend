package com.trilobitech.android

import com.trilobitech.ext.libs
import com.trilobitech.ext.versions

val libs = project.libs

plugins {
    id("com.android.application")
    id("com.trilobitech.commons.android")
}

android {
    resourcePrefix = ""

    defaultConfig {
        applicationId = rootProject.run { "${group}.${name.lowercase()}" }

        val version = libs.versions.project.get()
        versionName = version.name
        versionCode = version.code
    }

    buildTypes {
        getByName("debug") {
            versionNameSuffix = "-debug"
        }
    }
}

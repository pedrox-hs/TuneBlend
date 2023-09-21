package com.trilobitech.commons

import com.trilobitech.ext.android
import com.trilobitech.ext.libs
import com.trilobitech.ext.versions

plugins {
    kotlin("android")
    id("com.trilobitech.commons.kotlin")
}

val namespacePrefix: String = rootProject.group.toString()
val projectPath = project.path.removePrefix(":")

android {
    namespace = "${namespacePrefix}.${projectPath.replace(':', '.')}"
    resourcePrefix = projectPath.replace(':', '_')

    compileSdk = libs.versions.androidSdkCompile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidSdkMin.get().toInt()
    }

    compileOptions {
        val jdkVersion = JavaVersion.toVersion(libs.versions.jdk.get())

        sourceCompatibility = jdkVersion
        targetCompatibility = jdkVersion
    }

    buildTypes {
        getByName("debug") {
            enableUnitTestCoverage = fileTree(project.file("src")) {
                include(
                    "test*/**",
                    "sharedTest*/**",
                )
            }.isEmpty.not()

            enableAndroidTestCoverage = fileTree(project.file("src")) {
                include(
                    "androidTest*/**",
                    "sharedTest*/**",
                )
            }.isEmpty.not()
        }
    }
}

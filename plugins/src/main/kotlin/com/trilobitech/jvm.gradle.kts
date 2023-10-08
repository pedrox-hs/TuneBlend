package com.trilobitech

import com.trilobitech.ext.libs
import com.trilobitech.ext.versions

plugins {
    kotlin("jvm")
    id("com.trilobitech.commons.kotlin")
}

tasks.register("testJvmUnitTest") {
    description = "Run tests in JVM modules"
    group = JavaBasePlugin.VERIFICATION_GROUP

    dependsOn(project.tasks.matching {
        it is Test && it.name == "test"
    })
}

val javaVersion: JavaVersion = JavaVersion.toVersion(libs.versions.jdk.get())

kotlin {
    jvmToolchain(javaVersion.majorVersion.toInt())
}

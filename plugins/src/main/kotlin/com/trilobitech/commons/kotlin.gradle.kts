package com.trilobitech.commons

import com.trilobitech.ext.libs
import com.trilobitech.ext.versions

plugins {
    id("com.trilobitech.commons.quality")
}

val jvmVersion: String = libs.versions.jdk.get()

tasks.withType<JavaCompile> {
    sourceCompatibility = jvmVersion
    targetCompatibility = jvmVersion
}

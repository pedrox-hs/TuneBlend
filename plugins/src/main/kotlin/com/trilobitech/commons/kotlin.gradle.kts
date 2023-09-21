package com.trilobitech.commons

import com.trilobitech.ext.libs
import com.trilobitech.ext.versions

val jvmVersion: String = libs.versions.jdk.get()

tasks.withType<JavaCompile> {
    sourceCompatibility = jvmVersion
    targetCompatibility = jvmVersion
}

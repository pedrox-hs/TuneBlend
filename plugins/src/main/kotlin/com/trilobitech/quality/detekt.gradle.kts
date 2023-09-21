package com.trilobitech.quality

import com.trilobitech.ext.detekt
import com.trilobitech.ext.libs
import com.trilobitech.ext.versions

plugins {
    id("io.gitlab.arturbosch.detekt")
}

detekt {
    toolVersion = libs.versions.detekt.get()
    buildUponDefaultConfig = true
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}

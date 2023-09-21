package com.trilobitech.quality

import com.trilobitech.ext.libs
import com.trilobitech.ext.versions

plugins {
    id("jacoco")
}

jacoco {
    toolVersion = libs.versions.jaCoCo.get()
}

tasks.matching {
    it is Test
}.configureEach {
    jacoco {
        isIncludeNoLocationClasses = true
        excludes = listOf(
            "jdk.internal.*",
            "androidx.core.*",
            "com.android.*",
            "android.*",
        )
    }
}

tasks.matching {
    it is JacocoReport
}.configureEach {
    this as JacocoReport

    if (name == "jacocoTestReport") {
        dependsOn("test")
    }

    reports.apply {
        xml.required = true
        html.required = true
        csv.required = false
    }
}

fun Task.jacoco(block: JacocoTaskExtension.() -> Unit) =
    extensions.getByType<JacocoTaskExtension>().block()

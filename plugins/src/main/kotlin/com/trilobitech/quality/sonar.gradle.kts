package com.trilobitech.quality

import com.trilobitech.ext.sourceDirSets
import com.trilobitech.ext.testSourceDirSets
import java.util.Properties

plugins {
    id("org.sonarqube")
}

sonar {
    properties {
        property(
            "sonar.projectName",
            project.path.takeUnless { it == ":" }?.removePrefix(":") ?: project.name,
        )

        properties["sonar.sources"] = properties["sonar.sources"] ?: listOf<String>()
        properties["sonar.sources"] = listOf(
            properties["sonar.sources"],
            files(*project.sourceDirSets.toTypedArray()).asFileTree
                .matching {
                    include("**/*.java", "**/*.kt", "**/*.kts", "**/*.xml")
                }
                .files,
        )

        properties["sonar.tests"] = properties["sonar.tests"] ?: listOf<String>()
        properties["sonar.tests"] = listOf(
            properties["sonar.tests"],
            files(*project.testSourceDirSets.toTypedArray()).asFileTree
                .matching {
                    include("**/*Test.*")
                }
                .files,
        )

        property(
            "sonar.androidLint.reportPaths",
            fileTree(layout.buildDirectory) {
                include("reports/lint-results*.xml")
            }.files,
        )
        property(
            "sonar.kotlin.detekt.reportPaths",
            fileTree(layout.buildDirectory) {
                include("reports/detekt/**/*.xml")
            }.files,
        )

        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            fileTree(layout.buildDirectory) {
                include(
                    "reports/coverage/**/report.xml",
                    "reports/jacoco/**/*.xml",
                )
            }.files,
        )
        property(
            "sonar.coverageReportPaths",
            fileTree(layout.buildDirectory) {
                include(
                    "reports/coverage/**/report.xml",
                    "reports/jacoco/**/*.xml",
                )
            }.files,
        )

        property(
            "sonar.testExecutionReportPaths",
            fileTree(layout.buildDirectory.file("outputs")) {
                include(
                    "unit_test_code_coverage/**/*.exec",
                    "code_coverage/**/*.ec",
                )
            }.files,
        )

        val sonarProperties = project.file("sonar.properties")
        if (sonarProperties.exists()) {
            val properties = Properties().apply {
                load(sonarProperties.inputStream())
            }
            properties.forEach { (key, value) ->
                property(key.toString(), value)
            }
        }
    }
}

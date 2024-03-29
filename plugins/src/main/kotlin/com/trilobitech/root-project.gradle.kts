package com.trilobitech

import com.trilobitech.ext.libs
import com.trilobitech.ext.versions
import com.trilobitech.tasks.SonarPropertiesTask

plugins {
    id("org.sonarqube")
    id("com.trilobitech.quality.paparazzi")
}

sonar {
    properties {
        property("sonar.projectVersion", libs.versions.project.get().name)
    }
}

tasks.register("sonarProperties", SonarPropertiesTask::class)

tasks.register("unitTest") {
    description = "Run unit tests in all modules"
    group = JavaBasePlugin.VERIFICATION_GROUP

    dependsOn(provider {
        subprojects.flatMap { project ->
            project.tasks.matching { task ->
                task.name matches "^test(Debug|Jvm)UnitTest\$".toRegex()
            }
        }
    })
}

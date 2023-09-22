package com.trilobitech

import app.cash.paparazzi.gradle.PaparazziPlugin
import com.android.build.gradle.tasks.factory.AndroidUnitTest
import com.trilobitech.ext.libs
import com.trilobitech.ext.versions
import com.trilobitech.tasks.SonarPropertiesTask

plugins {
    id("org.sonarqube")
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

rootProject.takeUnless {
    gradle.startParameter.taskNames.any {
        it matches "recordPaparazzi".toRegex()
    }
}?.subprojects {
    afterEvaluate {
        if (!project.plugins.hasPlugin(PaparazziPlugin::class)) return@afterEvaluate

        tasks.matching {
            it is AndroidUnitTest
        }.whenTaskAdded {
            this as AndroidUnitTest
            finalizedBy(provider {
                val variantName = variantName.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase() else it.toString()
                }.removeSuffix("UnitTest")

                tasks.findByName("verifyPaparazzi${variantName}")
            })
        }
    }
}

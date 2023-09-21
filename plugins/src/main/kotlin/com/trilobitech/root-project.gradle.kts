package com.trilobitech

import com.android.build.gradle.tasks.factory.AndroidUnitTest

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

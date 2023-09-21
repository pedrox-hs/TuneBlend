package com.trilobitech

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

package com.trilobitech.tasks

import groovy.json.JsonBuilder
import org.gradle.api.DefaultTask
import org.gradle.kotlin.dsl.withType
import org.sonarqube.gradle.SonarTask

abstract class SonarPropertiesTask : DefaultTask() {
    init {
        group = "Sonar"
        description = "Prints the Sonar properties"

        super.doLast {
            val properties = getSonarProperties()
            val json = JsonBuilder(properties)

            logger.quiet(json.toPrettyString())
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun getSonarProperties(): Map<String, Any> {
        val properties = project.rootProject.tasks
            .withType<SonarTask>().firstOrNull()
            ?.properties?.orNull
            ?.filterKeys { key -> !key.endsWith(".libraries") }
            ?: return emptyMap()

        val transformedModule = mutableMapOf<String, Any>()

        properties.forEach { (key, value) ->
            val parts = key.removePrefix("sonar.").split(".")
            var currentMap = transformedModule

            parts.forEachIndexed { index, part ->
                if (index == parts.lastIndex) {
                    currentMap[part] = value
                } else {
                    currentMap = currentMap.getOrPut(part) {
                        mutableMapOf<String, Any>()
                    } as MutableMap<String, Any>
                }
            }
        }

        return transformedModule
    }
}

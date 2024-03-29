package com.trilobitech.ext

import org.gradle.api.artifacts.DependencyConstraint
import org.gradle.api.artifacts.dsl.DependencyConstraintHandler

fun DependencyConstraintHandler.addTestImplementation(
    name: String,
    block: DependencyConstraint.() -> Unit,
) {
    add("testImplementation", name, block)
}
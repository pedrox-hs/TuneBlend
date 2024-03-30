package com.trilobitech.ext

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType
import java.io.File

typealias AndroidCommonExtension = CommonExtension<*, *, *, *, *, *>

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

val Project.isAndroidModule: Boolean
    get() = extensions.findByType(CommonExtension::class) != null

val Project.android: AndroidCommonExtension
    get() = extensions.getByType(CommonExtension::class)

fun Project.android(
    block: AndroidCommonExtension.() -> Unit,
) = android.block()

fun Project.findSourceSets(
    predicate: (name: String) -> Boolean,
) =
    if (isAndroidModule) {
        android.sourceSets
            .filter { predicate(it.name) }
            .flatMap { it.srcDirs }
            .filter { file -> file.exists() }
            .toSet()
    } else emptySet()

val Project.sourceDirSets: Set<File>
    get() = findSourceSets {
        it matches "^(?!(androidT|t)est).*\$".toRegex()
    }

val Project.testSourceDirSets: Set<File>
    get() = findSourceSets {
        it matches "^(androidT|t)est(?!Fixtures).*\$".toRegex()
    }

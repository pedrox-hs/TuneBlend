package com.trilobitech.ext

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

typealias AndroidCommonExtension = CommonExtension<*, *, *, *, *>

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

val Project.android: CommonExtension<*, *, *, *, *>
    get() = extensions.getByType(CommonExtension::class)

fun Project.android(
    block: AndroidCommonExtension.() -> Unit,
) = android.block()

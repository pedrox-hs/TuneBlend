package com.trilobitech.ext

import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider

val VersionCatalog.detekt: DetektAccessors
    get() = DetektAccessors(this)

class DetektAccessors(
    catalog: VersionCatalog,
) {
    val formatting: Provider<MinimalExternalModuleDependency> =
        catalog.findLibrary("detekt.formatting").get()
}

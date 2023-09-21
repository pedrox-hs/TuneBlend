package com.trilobitech.ext

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.internal.provider.DefaultProviderFactory
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory

private const val VERSION_DELIMITER = "+"

val VersionCatalog.versions: VersionAccessors
    get() = VersionAccessors(this)

class VersionAccessors(
    private val catalog: VersionCatalog,
    providerFactory: ProviderFactory = DefaultProviderFactory(),
) {
    val project: Provider<Version> = providerFactory.provider {
        Version(catalog.findVersion("project").get().requiredVersion)
    }

    val jdk: Provider<String> = providerFactory.provider {
        catalog.findVersion("jdk").get().requiredVersion
    }

    val androidSdkCompile: Provider<String> = providerFactory.provider {
        catalog.findVersion("androidSdkCompile").get().requiredVersion
    }

    val androidSdkMin: Provider<String> = providerFactory.provider {
        catalog.findVersion("androidSdkMin").get().requiredVersion
    }

    val detekt: Provider<String> = providerFactory.provider {
        catalog.findVersion("detekt").get().requiredVersion
    }
}

data class Version(val name: String, val code: Int) {
    constructor(version: String) : this(
        version.substringBefore(VERSION_DELIMITER),
        version.substringAfter(VERSION_DELIMITER).toInt(),
    )

    override fun toString() = "$name$VERSION_DELIMITER$code"
}

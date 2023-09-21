package com.trilobitech.ext

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.internal.provider.DefaultProviderFactory
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory

val VersionCatalog.versions: VersionAccessors
    get() = VersionAccessors(this)

class VersionAccessors(
    private val catalog: VersionCatalog,
    providerFactory: ProviderFactory = DefaultProviderFactory(),
) {
    val jdk: Provider<String> = providerFactory.provider {
        catalog.findVersion("jdk").get().requiredVersion
    }
}

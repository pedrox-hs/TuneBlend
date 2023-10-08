package com.trilobitech.navigation.scanners

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.trilobitech.navigation.GENERATED_ROUTES_PACKAGE

internal class DefinitionsScanner(
    private val resolver: Resolver,
) {
    @OptIn(KspExperimental::class)
    fun scan(): List<String> =
        resolver.getDeclarationsFromPackage(GENERATED_ROUTES_PACKAGE)
            .filterIsInstance<KSFunctionDeclaration>()
            .map {
                it.simpleName.asString()
            }
            .toList()
}

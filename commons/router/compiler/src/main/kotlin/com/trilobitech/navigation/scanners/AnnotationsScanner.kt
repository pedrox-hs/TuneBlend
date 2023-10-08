package com.trilobitech.navigation.scanners

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.trilobitech.navigation.annotations.Route
import com.trilobitech.navigation.definitions.RoutesDefinition

internal class AnnotationsScanner(
    private val resolver: Resolver,
) {
    @OptIn(KspExperimental::class)
    fun scan(): RoutesDefinition {
        val symbols = resolver.getSymbolsWithAnnotation(Route::class.qualifiedName!!)

        val routes = symbols
            .filterIsInstance<KSFunctionDeclaration>()
            .mapNotNull { annotated ->
                val path = annotated.getAnnotationsByType(Route::class)
                    .firstOrNull()?.path ?: return@mapNotNull null

                val functionReference = annotated.qualifiedName?.asString()
                    ?: return@mapNotNull null

                path to functionReference
            }.toMap()

        return routes
    }
}

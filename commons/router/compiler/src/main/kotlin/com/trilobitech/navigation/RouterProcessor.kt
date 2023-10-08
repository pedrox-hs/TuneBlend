package com.trilobitech.navigation

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.trilobitech.navigation.generator.RouteCodeGenerator
import com.trilobitech.navigation.generator.asCode
import com.trilobitech.navigation.scanners.AnnotationsScanner
import com.trilobitech.navigation.scanners.DefinitionsScanner
import java.util.Locale

internal class RouterProcessor(
    private val logger: KSPLogger,
    private val options: Options,
    codeGenerator: CodeGenerator,
) : SymbolProcessor {

    private val routeCodeGenerator = RouteCodeGenerator(codeGenerator)

    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.info("Processing symbols for routes...")
        val routes = AnnotationsScanner(resolver).scan()
        val definitions = if (options.includeSubModules) {
            DefinitionsScanner(resolver).scan()
        } else {
            emptyList()
        }
        val functionName = generateRouteFunctionName()

        if (routes.isEmpty() && (definitions.isEmpty() || definitions.contains(functionName))) {
            logger.info("No symbols found!")
            return emptyList()
        }

        logger.logging("Generating routes function: $functionName")
        routeCodeGenerator.generateCode(
            functionName,
            routes.asCode(),
            definitions.asCode(),
        )

        return emptyList()
    }

    private fun generateRouteFunctionName(): String =
        "${options.projectPath}:routes"
            .split(":", "-", "_")
            .filter { it.isNotBlank() }
            .reduce { acc, part ->
                acc + part.replaceFirstChar { char ->
                    if (char.isLowerCase()) {
                        char.titlecase(Locale.ROOT)
                    } else {
                        char.toString()
                    }
                }
            }
}

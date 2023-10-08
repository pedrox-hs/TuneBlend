package com.trilobitech.navigation.generator

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.trilobitech.navigation.GENERATED_ROUTES_PACKAGE
import com.trilobitech.navigation.definitions.RoutesDefinition

class RouteCodeGenerator(
    private val codeGenerator: CodeGenerator,
) {
    fun generateCode(functionName: String, vararg code: String) {
        codeGenerator.createNewFile(
            dependencies = Dependencies(false),
            packageName = GENERATED_ROUTES_PACKAGE,
            fileName = functionName.replaceFirstChar {
                it.titlecase()
            },
        ).use { outputStream ->
            outputStream.write(
                """
                |package $GENERATED_ROUTES_PACKAGE
                |
                |import androidx.navigation.NavGraphBuilder
                |import androidx.navigation.compose.composable
                |
                |fun NavGraphBuilder.$functionName() {
                |${code.filter { it.isNotBlank() }.joinToString("\n")}
                |}
                """.trimMargin()
                    .encodeToByteArray()
            )
        }
    }
}

internal fun RoutesDefinition.asCode(): String =
    entries.joinToString("\n") { (path, functionReference) ->
        "   composable(\"$path\") {\n" +
            "       $functionReference()\n" +
            "   }"
    }

internal fun List<String>.asCode(): String =
    joinToString("\n") { functionReference ->
        "   $functionReference()"
    }

package com.trilobitech.navigation

import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.KotlinCompilation.ExitCode
import com.tschuchort.compiletesting.kspArgs
import com.tschuchort.compiletesting.kspSourcesDir
import com.tschuchort.compiletesting.symbolProcessorProviders
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.junit.Test

@OptIn(ExperimentalCompilerApi::class)
class RouterProcessorTest {
    @Test
    fun `should generate routes function`() {
        // arrange
        val compilation = KotlinCompilation().apply {
            sources = listOf(routeAnnotationSrc, loginScreenSrc)
            symbolProcessorProviders = listOf(RouterProcessorProvider())
            kspArgs = submoduleArgs
            messageOutputStream = System.out
        }

        // act
        val result = compilation.compile()

        // assert
        val generatedFiles = compilation.kspSourcesDir.walkTopDown().filter { it.isFile }.toList()
        val generatedFile = generatedFiles.firstOrNull()

        assertThat(result.exitCode, equalTo(ExitCode.OK))
        assertThat(generatedFiles.size, equalTo(1))
        assertThat(generatedFile?.name, equalTo("FeaturesFooBarRoutes.kt"))
        assertThat(generatedFile?.readText(), equalTo(generatedRoutesContent))
    }

    @Test
    fun `should generate routes function with submodules`() {
        // arrange
        val compilation = KotlinCompilation().apply {
            sources = listOf(routeAnnotationSrc, generatedRoutesSrc)
            symbolProcessorProviders = listOf(RouterProcessorProvider())
            kspArgs = moduleArgs
            messageOutputStream = System.out
        }

        // act
        val result = compilation.compile()

        // assert
        val generatedFiles = compilation.kspSourcesDir.walkTopDown().filter { it.isFile }.toList()
        val generatedFile = generatedFiles.firstOrNull()

        assertThat(result.exitCode, equalTo(ExitCode.OK))
        assertThat(generatedFiles.size, equalTo(1))
        assertThat(generatedFile?.name, equalTo("AppRoutes.kt"))
        assertThat(generatedFile?.readText(), equalTo(generatedRoutesWithSubmodulesContent))
    }
}

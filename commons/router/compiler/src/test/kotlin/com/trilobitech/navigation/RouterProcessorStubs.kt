package com.trilobitech.navigation

import com.tschuchort.compiletesting.SourceFile

val submoduleArgs = mutableMapOf(
    "com.trilobitech.navigation.PROJECT_PATH" to ":features:foo:bar",
)
val moduleArgs = mutableMapOf(
    "com.trilobitech.navigation.PROJECT_PATH" to ":app",
    "com.trilobitech.navigation.INCLUDE_SUBMODULES" to "true",
)

internal val routeAnnotationSrc = SourceFile.kotlin(
    name = "Route.kt",
    contents = """
    package com.trilobitech.navigation.annotations

    annotation class Route(val path: String)
    """.trimIndent(),
)

internal val loginScreenSrc = SourceFile.kotlin(
    name = "FooBarScreen.kt",
    contents = """
    package com.trilobitech.features.foo.bar

    import androidx.compose.runtime.Composable
    import com.trilobitech.navigation.annotations.Route

    @Composable
    @Route(path = "/foo/bar")
    fun FooBarScreen() { }
    """.trimIndent(),
)

internal val generatedRoutesContent = """
    package com.trilobitech.navigation.generated

    import androidx.navigation.NavGraphBuilder
    import androidx.navigation.compose.composable

    fun NavGraphBuilder.featuresFooBarRoutes() {
       composable("/foo/bar") {
           com.trilobitech.features.foo.bar.FooBarScreen()
       }
    }
""".trimIndent()

internal val generatedRoutesSrc = SourceFile.kotlin(
    name = "FeaturesFooBarRoutes.kt",
    contents = generatedRoutesContent,
)

internal val generatedRoutesWithSubmodulesContent = """
    package com.trilobitech.navigation.generated

    import androidx.navigation.NavGraphBuilder
    import androidx.navigation.compose.composable

    fun NavGraphBuilder.appRoutes() {
       featuresFooBarRoutes()
    }
""".trimIndent()

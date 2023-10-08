package com.trilobitech.navigation

internal const val ROUTES_PACKAGE_NAME = "com.trilobitech.navigation"
internal const val GENERATED_ROUTES_PACKAGE = "$ROUTES_PACKAGE_NAME.generated"

internal const val PROJECT_PATH_ARG_KEY = "$ROUTES_PACKAGE_NAME.PROJECT_PATH"
internal const val INCLUDE_SUBMODULES_ARG_KEY = "$ROUTES_PACKAGE_NAME.INCLUDE_SUBMODULES"

typealias Options = Map<String, String>

internal val Options.projectPath: String
    get() = getOrDefault(PROJECT_PATH_ARG_KEY, "")

internal val Options.includeSubModules: Boolean
    get() = getOrDefault(INCLUDE_SUBMODULES_ARG_KEY, "false").toBoolean()

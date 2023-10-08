package com.trilobitech.navigation

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class RouterProcessorProvider : SymbolProcessorProvider {
    override fun create(
        environment: SymbolProcessorEnvironment,
    ): SymbolProcessor =
        RouterProcessor(
            logger = environment.logger,
            options = environment.options,
            codeGenerator = environment.codeGenerator,
        )
}

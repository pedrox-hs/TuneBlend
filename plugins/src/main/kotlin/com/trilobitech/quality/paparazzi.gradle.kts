package com.trilobitech.quality

import app.cash.paparazzi.gradle.PaparazziPlugin
import com.android.build.gradle.tasks.factory.AndroidUnitTest
import com.trilobitech.ext.addTestImplementation
import org.gradle.api.attributes.java.TargetJvmEnvironment
import com.trilobitech.ext.libs
import com.trilobitech.ext.guava
import org.gradle.api.attributes.java.TargetJvmEnvironment.STANDARD_JVM
import org.gradle.api.attributes.java.TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE

subprojects {
    plugins.withType<PaparazziPlugin> {
        // Defer until afterEvaluate so that testImplementation is created by Android plugin.
        afterEvaluate {
            dependencies.constraints {
                addTestImplementation(libs.guava.get().module.toString()) {
                    attributes {
                        attribute(
                            TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
                            objects.named(TargetJvmEnvironment::class, STANDARD_JVM),
                        )
                    }
                    because(
                        """
                        LayoutLib and sdk-common depend on Guava's -jre published variant.
                        See https://github.com/cashapp/paparazzi/issues/906.
                        """.trimIndent()
                    )
                }
            }
        }
    }
}

rootProject.takeUnless {
    gradle.startParameter.taskNames.any {
        it matches "recordPaparazzi".toRegex()
    }
}?.subprojects {
    plugins.withType<PaparazziPlugin> {
        afterEvaluate {
            tasks.matching {
                it is AndroidUnitTest
            }.whenTaskAdded {
                require(this is AndroidUnitTest)

                finalizedBy(provider {
                    val variantName = variantName.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase() else it.toString()
                    }.removeSuffix("UnitTest")

                    tasks.findByName("verifyPaparazzi${variantName}")
                })
            }
        }
    }
}

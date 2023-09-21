plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.module.root)

    alias(libs.plugins.sonarCloud) apply false
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.detekt) apply false
}

group = "com.trilobitech"

apply {
    from("gradle/scripts/jacoco.gradle")
    from("gradle/scripts/detekt.gradle")
    from("gradle/scripts/sonar.gradle")
    from("gradle/scripts/paparazzi.gradle")
}

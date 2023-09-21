plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.module.root)

    alias(libs.plugins.sonarCloud) apply false
    alias(libs.plugins.paparazzi) apply false
}

group = "com.trilobitech"

apply {
    from("gradle/scripts/sonar.gradle")
}

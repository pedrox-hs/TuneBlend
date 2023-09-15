plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.sonarCloud) apply false
    alias(libs.plugins.detekt) apply false
}

apply {
    from("gradle/scripts/detekt.gradle")
    from("gradle/scripts/sonar.gradle")
}

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(kotlin("script-runtime"))
    implementation(libs.gradle.plugin.kotlin)
    implementation(libs.gradle.plugin.android)
    implementation(libs.gradle.plugin.detekt)
}

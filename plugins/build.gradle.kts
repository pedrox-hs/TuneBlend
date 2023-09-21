plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(kotlin("script-runtime"))
    implementation(libs.gradle.plugin.kotlin)
}

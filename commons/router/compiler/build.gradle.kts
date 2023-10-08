plugins {
    id(libs.plugins.module.jvm.get().pluginId)
}

dependencies {
    implementation(libs.kspApi)
    implementation(projects.commons.router.annotations)
    testImplementation(libs.bundles.testing.unit)
    testImplementation(libs.testing.ksp)
}

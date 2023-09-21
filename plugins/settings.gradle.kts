pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    versionCatalogs {
        create(defaultLibrariesExtensionName.get()) {
            val file = file("../gradle/libs.versions.toml")
            if (file.exists().not()) {
                file("libs.versions.example.toml").copyTo(file, true)
            }
            from(files(file))
        }
    }
}

rootProject.name = "Plugins"

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.api.AndroidBasePlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
}

allprojects {
    afterEvaluate {
        val jvmVersion = libs.versions.jdk.get()
        val compileSdkVersion = libs.versions.compileSdk.get()
        val minSdkVersion = libs.versions.minSdk.get()

        applyCommonAndroidConfig(
            jvmVersion = JavaVersion.toVersion(jvmVersion),
            compileSdkVersion = compileSdkVersion.toInt(),
            minSdkVersion = minSdkVersion.toInt(),
        )

        tasks.withType<JavaCompile> {
            sourceCompatibility = jvmVersion
            targetCompatibility = jvmVersion
        }

        tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = jvmVersion
            }
        }
    }
}

fun Project.applyCommonAndroidConfig(
    jvmVersion: JavaVersion,
    compileSdkVersion: Int,
    minSdkVersion: Int,
) {
    if (!plugins.hasPlugin(AndroidBasePlugin::class.java)) return

    val android = extensions.getByType(CommonExtension::class.java)

    android.apply {
        compileSdk = compileSdkVersion

        defaultConfig {
            minSdk = minSdkVersion
        }

        compileOptions {
            sourceCompatibility = jvmVersion
            targetCompatibility = jvmVersion
        }
    }
}

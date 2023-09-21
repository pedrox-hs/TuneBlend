@file:Suppress("DEPRECATION")

package com.trilobitech.ext

import com.android.build.api.dsl.AndroidSourceSet
import com.android.build.gradle.api.AndroidSourceDirectorySet
import java.io.File

val AndroidSourceSet.srcDirs: Set<File>
    get() = (kotlin as? AndroidSourceDirectorySet)?.srcDirs?.toSet() ?: emptySet()

package com.trilobitech.commons.ui.util

import kotlin.reflect.full.declaredMemberProperties

@Suppress("UNCHECKED_CAST")
internal fun <T> Any.readField(fieldName: String): T {
    val clazz = javaClass.kotlin
    val field = clazz.declaredMemberProperties.first {
        it.name == fieldName
    }
    return field.getter.call(this) as T
}

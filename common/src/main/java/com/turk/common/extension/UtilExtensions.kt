package com.turk.common.extension

import android.webkit.MimeTypeMap
import java.io.File

fun File.getMimeType(fallback: String = "image/*"): String {
    return MimeTypeMap.getFileExtensionFromUrl(toString())
            ?.run { MimeTypeMap.getSingleton().getMimeTypeFromExtension(toLowerCase()) }
            ?: fallback // You might set it to */*
}

const val MAX_LENGTH: Int = 13

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= MAX_LENGTH) tag else tag.substring(0, MAX_LENGTH)
    }


// use TAG function but for that while using LOG we have to add method parenthesis at the end of TAG()
inline fun <reified T> T.tag(): String = T::class.java.simpleName

/**
 * This is used to make when an expression which force to implement all Branches of when Block
 */
val <T> T.exhaustive: T get() = this
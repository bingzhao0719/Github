package com.bing.github.kotlin.utils

import java.util.*

object StringUtils {

    fun getSizeString(size: Long): String? {
        return when {
            size < 1024 -> String.format(Locale.getDefault(), "%d B", size)
            size < 1024 * 1024 -> {
                val sizeK = size / 1024f
                String.format(Locale.getDefault(), "%.2f KB", sizeK)
            }
            size < 1024 * 1024 * 1024 -> {
                val sizeM = size / (1024f * 1024f)
                String.format(Locale.getDefault(), "%.2f MB", sizeM)
            }
            else -> null
        }
    }
}
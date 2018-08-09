package com.bing.github.kotlin.utils

import android.content.Context
import android.graphics.Color
import android.support.annotation.ColorInt
import com.bing.github.kotlin.R

object ViewUtils {

    @ColorInt
    fun getAccentColor(context: Context): Int {
        return getColorAttr(context, R.attr.colorAccent)
    }

    @ColorInt
    private fun getColorAttr(context: Context, attr: Int): Int {
        val theme = context.theme
        val typedArray = theme.obtainStyledAttributes(intArrayOf(attr))
        val color = typedArray.getColor(0, Color.LTGRAY)
        typedArray.recycle()
        return color
    }

    fun getWindowBackground(context: Context): Int {
        return getColorAttr(context, android.R.attr.windowBackground)
    }
}
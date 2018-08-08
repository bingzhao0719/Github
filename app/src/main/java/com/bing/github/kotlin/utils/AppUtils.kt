package com.bing.github.kotlin.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.bing.github.kotlin.MyApp
import com.bing.github.kotlin.R
import java.text.SimpleDateFormat
import java.util.*

object AppUtils {

    val LANGUAGE = "language"
    private val DATE_REGEX_MAP = HashMap<Locale, String>()

    init {
        DATE_REGEX_MAP[Locale.CHINA] = "yyyy-MM-dd";
        DATE_REGEX_MAP[Locale.TAIWAN] = "yyyy-MM-dd";
        DATE_REGEX_MAP[Locale.ENGLISH] = "MMM d, yyyy";
        DATE_REGEX_MAP[Locale.GERMAN] = "dd.MM.yyyy";
        DATE_REGEX_MAP[Locale.GERMANY] = "dd.MM.yyyy";
    }

    fun getDateStr(date: Date): String {
        val locale = getLocale(getLanguage())
        val regex = if (DATE_REGEX_MAP.containsKey(locale)) DATE_REGEX_MAP[locale] else "yyyy-MM-dd"
        val format = SimpleDateFormat(regex, locale)
        return format.format(date)
    }

    fun getNewsTimeStr(context: Context, date: Date): String {
        val subTime = System.currentTimeMillis() - date.time
        val MILLIS_LIMIT = 1000.0
        val SECONDS_LIMIT = 60 * MILLIS_LIMIT
        val MINUTES_LIMIT = 60 * SECONDS_LIMIT
        val HOURS_LIMIT = 24 * MINUTES_LIMIT
        val DAYS_LIMIT = 30 * HOURS_LIMIT
        return if (subTime < MILLIS_LIMIT) {
            context.getString(R.string.just_now)
        } else if (subTime < SECONDS_LIMIT) {
            Math.round(subTime / MILLIS_LIMIT).toString() + " " + context.getString(R.string.seconds_ago)
        } else if (subTime < MINUTES_LIMIT) {
            Math.round(subTime / SECONDS_LIMIT).toString() + " " + context.getString(R.string.minutes_ago)
        } else if (subTime < HOURS_LIMIT) {
            Math.round(subTime / MINUTES_LIMIT).toString() + " " + context.getString(R.string.hours_ago)
        } else if (subTime < DAYS_LIMIT) {
            Math.round(subTime / HOURS_LIMIT).toString() + " " + context.getString(R.string.days_ago)
        } else
            getDateStr(date)
    }

    fun getLanguage(): String {
        return getDefaultSp(MyApp.get()!!).getString(LANGUAGE, "en")
    }

    fun getDefaultSp(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getLocale(language: String): Locale {
        val locale: Locale
        if (language.equals("zh-rCN", ignoreCase = true)) {
            return Locale.SIMPLIFIED_CHINESE
        } else if (language.equals("zh-rTW", ignoreCase = true)) {
            return Locale.TRADITIONAL_CHINESE
        }
        val array = language.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (array.size > 1) {
            locale = Locale(array[0], array[1])
        } else {
            locale = Locale(language)
        }
        return locale
    }
}
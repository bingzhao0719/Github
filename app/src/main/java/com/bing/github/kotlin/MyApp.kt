package com.bing.github.kotlin

import android.app.Application
import android.content.Context

open class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        context = base
    }

    companion object {
        private lateinit var context: Context
        fun get(): Context {
            return context
        }
    }
}
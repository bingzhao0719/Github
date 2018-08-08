package com.bing.github.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bing.github.kotlin.utils.ThemeHelper

open class BaseActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeHelper.apply(this)
    }
}
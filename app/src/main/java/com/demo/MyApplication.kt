package com.demo

import android.app.Application
import android.content.Context


class MyApplication : Application() {

    val NIGHT_MODE = "NIGHT_MODE"
    private var isNightModeEnabled = false
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        sInstance = this
    }

    companion object {
        lateinit var sInstance: MyApplication
        lateinit var context: Context
    }
}
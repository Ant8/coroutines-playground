package com.abm.ant8.coroutinesplayground

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid

class WrcResultsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }
}
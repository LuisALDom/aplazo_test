package com.swyt.aplazotest

import androidx.multidex.MultiDexApplication
import com.swyt.aplazotest.di.Modules

class App:  MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Modules.init(this)
    }
}
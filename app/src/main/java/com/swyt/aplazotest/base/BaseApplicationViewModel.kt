package com.swyt.aplazotest.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

open class BaseApplicationViewModel(private  val app: Application): AndroidViewModel(app), ILogBase {

    override fun logDebug(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun logInfo(tag: String, message: String) {
        Log.i(tag, message)
    }

    override fun logWarning(tag: String, message: String) {
        Log.w(tag, message)
    }

    override fun logError(tag: String, message: String) {
        Log.e(tag, message)
    }

}
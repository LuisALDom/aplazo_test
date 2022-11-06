package com.swyt.aplazotest.base

interface ILogBase {
    fun logDebug(tag: String, message: String)
    fun logInfo(tag: String, message: String)
    fun logWarning(tag: String, message: String)
    fun logError(tag: String, message: String)
}
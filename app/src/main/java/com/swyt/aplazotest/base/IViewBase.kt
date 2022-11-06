package com.swyt.aplazotest.base

interface IViewBase {
    fun toast(message: String)
    fun toastLong(message: String)
    fun blockUI()
    fun unblockUI()
}
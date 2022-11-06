package com.swyt.aplazotest.base

interface IFragmentBase {
    fun showSnackBarError(message: String)
    fun showSnackBarSuccess(message: String)
    fun showProgressBar()
    fun hideProgressBar()
    fun finishActivity()
    fun workInProgress()
    fun workDone()
}
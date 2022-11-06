package com.swyt.aplazotest.base

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.swyt.aplazotest.ui.activity.MainActivity
import com.swyt.aplazotest.utils.observerOn

open class BaseFragment: Fragment(), ILogBase, IViewBase, IFragmentBase  {

    private val parentActivity: MainActivity by lazy {
        activity as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPress()
    }

    //livedataHelper
    fun <T> LiveData<T>.observerInside(observer: (t: T) -> Unit) {
        this.observerOn(viewLifecycleOwner) {
            it?.let(observer)
        }
    }

    //onBackPress setup
    open fun onBackPressFunction() {}

    private fun onBackPress() {
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressFunction()
                }
            })
    }

    fun finishParentActivity(){
        parentActivity.finish()
    }

    override fun showSnackBarError(message:String) {
        parentActivity.showSnackBarError(message)
    }

    override fun showSnackBarSuccess(message: String) {
        parentActivity.showSnackBarSuccess(message)
    }

    override fun showProgressBar() {
        parentActivity.showProgressCard()
    }

    override fun hideProgressBar() {
        parentActivity.hideProgressCard()
    }

    override fun finishActivity() {
        parentActivity.finish()
    }

    override fun workInProgress() {
        parentActivity.apply {
            blockUI()
        }
    }

    override fun workDone() {
        parentActivity.apply {
            unblockUI()
        }
    }

    //LOG
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

    //TOAST
    override fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun toastLong(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun blockUI() {
        parentActivity.blockUI()
    }

    override fun unblockUI() {
        parentActivity.unblockUI()
    }

}
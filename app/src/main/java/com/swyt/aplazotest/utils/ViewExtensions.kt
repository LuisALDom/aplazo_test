package com.swyt.aplazotest.utils

import android.view.View

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

fun View.isNotVisible(): Boolean {
    return this.visibility == View.GONE
}

fun View.enabled(){
    this.isEnabled = true
}

fun View.unenabled(){
    this.isEnabled = false
}
package com.swyt.aplazotest.utils

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

fun AppCompatImageView.load(url: String) {
    val glideUrl = GlideUrl(
        url,
        LazyHeaders.Builder().addHeader("User-Agent", USER_AGENT).build()
    )
    Glide.with(this.context).load(glideUrl).into(this)
}
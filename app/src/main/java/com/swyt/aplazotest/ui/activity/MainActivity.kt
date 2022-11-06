package com.swyt.aplazotest.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.swyt.aplazotest.base.BaseActivity
import com.swyt.aplazotest.databinding.ActivityMainBinding
import com.swyt.aplazotest.utils.hide
import com.swyt.aplazotest.utils.show

class MainActivity : BaseActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun showSnackBarError(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(this, android.R.color.holo_red_light))
            .setTextColor(ContextCompat.getColor(this, android.R.color.white))
            .show()
    }

    fun showSnackBarSuccess(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(this, android.R.color.holo_green_dark))
            .setTextColor(ContextCompat.getColor(this, android.R.color.white))
            .show()
    }

    fun showProgressCard() {
        binding.progressCardMaterial.show()
    }

    fun hideProgressCard() {
        binding.progressCardMaterial.hide()
    }
}
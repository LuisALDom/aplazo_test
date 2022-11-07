package com.swyt.aplazotest.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swyt.provider.usecases.UserGetListCategoriesUseCase

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val application: Application,
    private val getListCategoriesUseCase: UserGetListCategoriesUseCase
):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(CategoriesViewModel::class.java) -> CategoriesViewModel(application, getListCategoriesUseCase)
                else -> throw IllegalAccessException("Unknown ViewModel class you must add it")
            }
        } as T
    }

}
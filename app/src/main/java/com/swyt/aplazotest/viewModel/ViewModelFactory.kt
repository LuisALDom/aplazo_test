package com.swyt.aplazotest.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swyt.provider.usecases.*

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val application: Application,
    private val getListCategoriesUseCase: UserGetListCategoriesUseCase,
    private val getListMealUseCase: UserGetListMealUseCase,
    private val getDetailMealUseCase: UserGetDetailMealUseCase,
    private val getSearchMealUseCase: UserGetSearchMealUseCase,
    private val getRandomMealUseCase: UserGetRandomMealUseCase
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(CategoriesViewModel::class.java) -> CategoriesViewModel(
                    application,
                    getListCategoriesUseCase,
                    getSearchMealUseCase,
                    getRandomMealUseCase
                )
                isAssignableFrom(MealsViewModel::class.java) -> MealsViewModel(
                    application,
                    getListMealUseCase
                )
                isAssignableFrom(DetailMealViewModel::class.java) -> DetailMealViewModel(
                    application,
                    getDetailMealUseCase
                )
                else -> throw IllegalAccessException("Unknown ViewModel class you must add it")
            }
        } as T
    }

}
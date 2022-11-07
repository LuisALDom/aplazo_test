package com.swyt.aplazotest.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.swyt.provider.actions.Result
import com.swyt.aplazotest.actions.CategoriesAction
import com.swyt.aplazotest.base.BaseApplicationViewModel
import com.swyt.provider.usecases.UserGetListCategoriesUseCase
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val app: Application,
    private val getListCategoriesUseCase: UserGetListCategoriesUseCase
) : BaseApplicationViewModel(app) {

    init {
        getListCategories()
    }

    //Consume Service Categories on ViewModel
    private val _listCategories: MutableLiveData<CategoriesAction> by lazy {
        MutableLiveData<CategoriesAction>().apply {
            value = CategoriesAction.init
        }
    }
    val listCategories: LiveData<CategoriesAction> get() = _listCategories

    fun getListCategories() {
        viewModelScope.launch {
            when(val result = getListCategoriesUseCase()) {
                is Result.Success -> {
                    _listCategories.postValue(CategoriesAction.CategoriesData(data = result.value))
                }
                is Result.Failure -> {
                    _listCategories.postValue(CategoriesAction.Error(message = result.error))
                }
            }
        }
    }
}
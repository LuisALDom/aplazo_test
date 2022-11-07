package com.swyt.aplazotest.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.swyt.provider.actions.Result
import com.swyt.aplazotest.actions.MealsAction
import com.swyt.aplazotest.base.BaseApplicationViewModel
import com.swyt.provider.usecases.UserGetListMealUseCase
import kotlinx.coroutines.launch

class MealsViewModel(
    private val app: Application,
    private val getListMealUseCase: UserGetListMealUseCase
):BaseApplicationViewModel(app) {

    //Consume Service Meals on ViewModel
    private val _listMeals: MutableLiveData<MealsAction> by lazy {
        MutableLiveData<MealsAction>().apply {
            value = MealsAction.init
        }
    }
    val listMeals: LiveData<MealsAction> get() = _listMeals

    fun getListMeals(category: String) {
        viewModelScope.launch {
            when(val result = getListMealUseCase(category = category)) {
                is Result.Success -> {
                    _listMeals.postValue(MealsAction.MealData(data = result.value))
                }
                is Result.Failure -> {
                    _listMeals.postValue(MealsAction.Error(message = result.error))
                }
            }
        }
    }

    fun resetVM() {
        _listMeals.value = MealsAction.init
    }
}
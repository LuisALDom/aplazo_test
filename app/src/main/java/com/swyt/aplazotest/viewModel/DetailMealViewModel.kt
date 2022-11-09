package com.swyt.aplazotest.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.swyt.provider.actions.Result
import com.swyt.aplazotest.actions.DetailMealAction
import com.swyt.aplazotest.base.BaseApplicationViewModel
import com.swyt.provider.usecases.UserGetDetailMealUseCase
import kotlinx.coroutines.launch

class DetailMealViewModel(
    private val app: Application,
    private val getDetailMealUseCase: UserGetDetailMealUseCase
): BaseApplicationViewModel(app) {

    //Consume Service Detail Meals on ViewModel
    private val _detailMeal: MutableLiveData<DetailMealAction> by lazy {
        MutableLiveData<DetailMealAction>().apply {
            value = DetailMealAction.init
        }
    }
    val detailMeal: LiveData<DetailMealAction> get() = _detailMeal

    fun getDetailMeal(idMeal: String) {
        viewModelScope.launch {
            when(val result = getDetailMealUseCase(idMeal = idMeal)) {
                is Result.Success -> {
                    _detailMeal.postValue(DetailMealAction.DetailMealData(data = result.value))
                }
                is Result.Failure -> {
                    _detailMeal.postValue(DetailMealAction.Error(message = result.error))
                }
            }
        }
    }

}
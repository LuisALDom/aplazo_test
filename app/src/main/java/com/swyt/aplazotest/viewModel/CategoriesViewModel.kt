package com.swyt.aplazotest.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.swyt.provider.actions.Result
import com.swyt.aplazotest.actions.CategoriesAction
import com.swyt.aplazotest.actions.RandomMealAction
import com.swyt.aplazotest.actions.SearchMealAction
import com.swyt.aplazotest.base.BaseApplicationViewModel
import com.swyt.provider.usecases.UserGetListCategoriesUseCase
import com.swyt.provider.usecases.UserGetRandomMealUseCase
import com.swyt.provider.usecases.UserGetSearchMealUseCase
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val app: Application,
    private val getListCategoriesUseCase: UserGetListCategoriesUseCase,
    private val getSearchMealUseCase: UserGetSearchMealUseCase,
    private val getRandomMealUseCase: UserGetRandomMealUseCase
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

    //Consume Service Search Meal
    private val _searchMeal: MutableLiveData<SearchMealAction> by lazy {
        MutableLiveData<SearchMealAction>().apply {
            value = SearchMealAction.init
        }
    }
    val searchMeal: LiveData<SearchMealAction> get() = _searchMeal

    fun searchMealByLetter(letterSearch: String) {
        viewModelScope.launch {
            when(val result = getSearchMealUseCase(letter = letterSearch)) {
                is Result.Success -> {
                    _searchMeal.postValue(SearchMealAction.SearchMealData(data = result.value))
                }
                is Result.Failure -> {
                    _searchMeal.postValue(SearchMealAction.Error(message = result.error))
                }
            }
        }
    }

    //Consume Service Random Meal
    private val _randomMeal: MutableLiveData<RandomMealAction> by lazy {
        MutableLiveData<RandomMealAction>().apply {
            value = RandomMealAction.init
        }
    }
    val randomMeal: LiveData<RandomMealAction> get() = _randomMeal

    fun randomMeal() {
        viewModelScope.launch {
            when(val result = getRandomMealUseCase()) {
                is Result.Success -> {
                    _randomMeal.postValue(RandomMealAction.RandomMealData(data = result.value))
                }
                is Result.Failure -> {
                    _randomMeal.postValue(RandomMealAction.Error(message = result.error))
                }
            }
        }
    }

    fun resetCategoriesVM() {
        _listCategories.value = CategoriesAction.init
    }

    fun resetSearchVM() {
        _searchMeal.value = SearchMealAction.init
    }
}
package com.swyt.aplazotest.actions

import com.swyt.provider.model.DetailMealResponse

sealed class SearchMealAction {
    object init: SearchMealAction()
    data class SearchMealData(val data: DetailMealResponse): SearchMealAction()
    data class Error(val message: String): SearchMealAction()
}
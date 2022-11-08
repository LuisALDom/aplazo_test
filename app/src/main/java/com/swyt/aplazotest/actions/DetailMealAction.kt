package com.swyt.aplazotest.actions

import com.swyt.provider.model.DetailMealResponse

sealed class DetailMealAction {
    object init: DetailMealAction()
    data class DetailMealData(val data: DetailMealResponse): DetailMealAction()
    data class Error(val message: String): DetailMealAction()
}
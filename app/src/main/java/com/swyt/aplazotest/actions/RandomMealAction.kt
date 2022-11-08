package com.swyt.aplazotest.actions

import com.swyt.provider.model.DetailMealResponse

sealed class RandomMealAction {
    object init: RandomMealAction()
    data class RandomMealData(val data: DetailMealResponse): RandomMealAction()
    data class Error(val message: String): RandomMealAction()
}
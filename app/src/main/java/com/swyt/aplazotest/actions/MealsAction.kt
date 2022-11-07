package com.swyt.aplazotest.actions

import com.swyt.provider.model.ListMealsResponse

sealed class MealsAction {
    object init: MealsAction()
    data class MealData(val data: ListMealsResponse): MealsAction()
    data class Error(val message: String): MealsAction()
}
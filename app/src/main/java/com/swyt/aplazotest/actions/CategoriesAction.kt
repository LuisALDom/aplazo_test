package com.swyt.aplazotest.actions

import com.swyt.provider.model.ListCategoriesResponse

sealed class CategoriesAction {
    object init: CategoriesAction()
    data class CategoriesData(val data: ListCategoriesResponse): CategoriesAction()
    data class Error(val message: String): CategoriesAction()
}
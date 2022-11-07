package com.swyt.provider.usecases

import com.swyt.provider.actions.Result
import com.swyt.provider.model.ListCategoriesResponse
import com.swyt.provider.model.ListMealsResponse
import com.swyt.provider.services.GetListMealsServices

internal class UserGetListMealsUseCaseImpl(private val getListMealsServices: GetListMealsServices) :
    UserGetListMealUseCase {
    override suspend fun invoke(category: String): Result<ListMealsResponse> {
        return try {
            val response = getListMealsServices.getListMeals(category)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success<ListMealsResponse>(value = it)
                } ?: run {
                    Result.Failure("Sin datos en Categorias")
                }
            } else {
                Result.Failure("Sin datos en Categorias")
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            Result.Failure("Sin datos en Categorias")
        }
    }

}

interface UserGetListMealUseCase {
    suspend operator fun invoke(category: String): Result<ListMealsResponse>
}
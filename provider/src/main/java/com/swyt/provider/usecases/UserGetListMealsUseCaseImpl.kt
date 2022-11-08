package com.swyt.provider.usecases

import com.swyt.provider.actions.Result
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
                    Result.Failure("Sin datos en Comidas")
                }
            } else {
                Result.Failure("Sin datos en Comidas")
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            Result.Failure("Sin datos en Comidas")
        }
    }

}

interface UserGetListMealUseCase {
    suspend operator fun invoke(category: String): Result<ListMealsResponse>
}
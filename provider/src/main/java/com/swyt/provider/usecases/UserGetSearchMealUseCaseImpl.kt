package com.swyt.provider.usecases

import com.swyt.provider.actions.Result
import com.swyt.provider.model.DetailMealResponse
import com.swyt.provider.services.GetSearchMealServices

internal class UserGetSearchMealUseCaseImpl(private val getSearchMealServices: GetSearchMealServices):
UserGetSearchMealUseCase{
    override suspend fun invoke(letter: String): Result<DetailMealResponse> {
        return try {
            val response = getSearchMealServices.getSearchMeal(letter)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success<DetailMealResponse>(value = it)
                } ?: run {
                    Result.Failure("Sin datos en la busqueda")
                }
            } else {
                Result.Failure("Sin datos en la busqueda")
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            Result.Failure("Sin datos en la busqueda")
        }
    }

}

interface UserGetSearchMealUseCase {
    suspend operator fun invoke(letter: String): Result<DetailMealResponse>
}
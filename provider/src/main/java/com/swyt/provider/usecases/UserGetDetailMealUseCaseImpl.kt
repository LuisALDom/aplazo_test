package com.swyt.provider.usecases

import com.swyt.provider.actions.Result
import com.swyt.provider.model.DetailMealResponse
import com.swyt.provider.services.GetDetailMealServices

internal class UserGetDetailMealUseCaseImpl(private val getDetailMealServices: GetDetailMealServices) :
    UserGetDetailMealUseCase {
    override suspend fun invoke(idMeal: String): Result<DetailMealResponse> {
        return try {
            val response = getDetailMealServices.getDetailMeal(idMeal = idMeal)
            if (response.isSuccessful) {
                response.body()?.let {
                   Result.Success<DetailMealResponse>(value = it)
                } ?: run {
                    Result.Failure("Sin datos en el detalle")
                }
            } else {
                Result.Failure("Sin datos en el detalle")
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            Result.Failure("Sin datos en el detalle")
        }
    }

}

interface UserGetDetailMealUseCase {
    suspend operator fun invoke(idMeal: String): Result<DetailMealResponse>
}
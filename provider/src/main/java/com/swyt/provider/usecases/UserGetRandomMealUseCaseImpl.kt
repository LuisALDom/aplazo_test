package com.swyt.provider.usecases

import com.swyt.provider.actions.Result
import com.swyt.provider.model.DetailMealResponse
import com.swyt.provider.services.GetRandomMealServices

internal class UserGetRandomMealUseCaseImpl(private val getRandomMealServices: GetRandomMealServices) :
    UserGetRandomMealUseCase {
    override suspend fun invoke(): Result<DetailMealResponse> {
        return try {
            val response = getRandomMealServices.getRandomMeal()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success<DetailMealResponse>(value = it)
                } ?: run {
                    Result.Failure("Sin datos Random")
                }
            } else {
                Result.Failure("Sin datos Random")
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            Result.Failure("Sin datos Random")
        }
    }

}

interface UserGetRandomMealUseCase {
    suspend operator fun invoke(): Result<DetailMealResponse>
}
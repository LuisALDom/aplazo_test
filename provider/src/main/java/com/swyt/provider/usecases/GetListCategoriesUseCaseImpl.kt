package com.swyt.provider.usecases

import com.swyt.provider.actions.Result
import com.swyt.provider.model.ListCategoriesResponse
import com.swyt.provider.services.GetListCategoriesServices

internal class UserGetListCategoriesUseCaseImpl(private val getListCategoriesServices: GetListCategoriesServices) :
    UserGetListCategoriesUseCase {
    override suspend fun invoke(): Result<ListCategoriesResponse> {
        return try {
            val response = getListCategoriesServices.getListCategories()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success<ListCategoriesResponse>(value = it)
                } ?: run {
                    Result.Failure("Sin datos en Categorias")
                }
            } else {
                Result.Failure("Sin datos en Categorias")
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            com.swyt.provider.actions.Result.Failure("Sin datos en Categorias")
        }
    }
}

interface UserGetListCategoriesUseCase {
    suspend operator fun invoke(): Result<ListCategoriesResponse>
}
package com.swyt.provider.services

import com.swyt.provider.model.ListMealsResponse
import com.swyt.provider.utils.URLServices
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetListMealsServices {

    @GET(URLServices.GET_LIST_MEALS)
    suspend fun getListMeals(
        @Query("c") type: String
    ): Response<ListMealsResponse>
}
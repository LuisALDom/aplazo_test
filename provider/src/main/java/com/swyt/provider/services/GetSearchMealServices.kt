package com.swyt.provider.services

import com.swyt.provider.model.DetailMealResponse
import com.swyt.provider.utils.URLServices
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetSearchMealServices {

    @GET(URLServices.GET_SEARCH_MEAL)
    suspend fun getSearchMeal(
        @Query("f") nameMeal: String
    ): Response<DetailMealResponse>
}
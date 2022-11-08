package com.swyt.provider.services

import com.swyt.provider.model.DetailMealResponse
import com.swyt.provider.utils.URLServices
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDetailMealServices {

    @GET(URLServices.GET_DETAIL_MEAL)
    suspend fun getDetailMeal(
        @Query("i") idMeal: String
    ): Response<DetailMealResponse>
}
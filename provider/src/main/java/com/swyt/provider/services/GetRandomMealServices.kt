package com.swyt.provider.services

import com.swyt.provider.model.DetailMealResponse
import com.swyt.provider.utils.URLServices
import retrofit2.Response
import retrofit2.http.GET

interface GetRandomMealServices {

    @GET(URLServices.GET_RANDOM_MEAL)
    suspend fun getRandomMeal(): Response<DetailMealResponse>
}
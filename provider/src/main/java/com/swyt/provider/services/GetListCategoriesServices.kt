package com.swyt.provider.services

import com.swyt.provider.model.ListCategoriesResponse
import com.swyt.provider.utils.URLServices
import retrofit2.Response
import retrofit2.http.GET

interface GetListCategoriesServices {

    //GetListCategoriesServices Categories
    @GET(URLServices.GET_LIST_CATEGORIES)
    suspend fun getListCategories(): Response<ListCategoriesResponse>
}
package com.swyt.provider.utils

import com.swyt.provider.BuildConfig

object URLServices {

    //Get list Categoriess
    const val GET_LIST_CATEGORIES = "${BuildConfig.BASE_URL}api/json/v1/1/categories.php"

    //Get meal By Category
    const val GET_LIST_MEALS = "${BuildConfig.BASE_URL}api/json/v1/1/filter.php"

    //Get meal detail
    const val GET_DETAIL_MEAL = "${BuildConfig.BASE_URL}api/json/v1/1/lookup.php"

    //Get a search
    const val GET_SEARCH_MEAL = "${BuildConfig.BASE_URL}api/json/v1/1/search.php"

    //Get random
    const val GET_RANDOM_MEAL = "${BuildConfig.BASE_URL}api/json/v1/1/random.php"
}
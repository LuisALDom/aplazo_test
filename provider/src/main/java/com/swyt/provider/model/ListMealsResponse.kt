package com.swyt.provider.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListMealsResponse (
        @SerializedName("meals") val meals: MutableList<Meal> = arrayListOf()
) : Serializable

data class Meal(
    @SerializedName("strMeal") val nameMeal: String? = null,
    @SerializedName("strMealThumb") val imageMeal: String? = null,
    @SerializedName("idMeal") val idMeal: String? = null
) : Serializable
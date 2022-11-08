package com.swyt.provider.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DetailMealResponse (
    @SerializedName("meals") val meal: MutableList<DetailMeal> = arrayListOf()
) : Serializable

data class DetailMeal(
    @SerializedName("idMeal") val idMeal: String? = null,
    @SerializedName("strMeal") val nameMeal: String? = null,
    @SerializedName("strCategory") val categoryMeal: String? = null,
    @SerializedName("strInstructions") val instructions: String? = null,
    @SerializedName("strMealThumb") val imageMeal: String? = null,
    @SerializedName("strYoutube") val linkYoutube: String? = null,
    @SerializedName("strSource") val source: String? = null
) : Serializable
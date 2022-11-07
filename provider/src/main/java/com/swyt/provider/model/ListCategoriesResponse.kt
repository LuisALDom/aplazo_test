package com.swyt.provider.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListCategoriesResponse (
    @SerializedName("categories") val categories: MutableList<Category> = arrayListOf()
) : Serializable

data class Category(
    @SerializedName("idCategory") val idCategory: String? = null,
    @SerializedName("strCategory") val nameCategory: String? = null,
    @SerializedName("strCategoryThumb") val imageCategory: String? = null,
    @SerializedName("strCategoryDescription") val descriptionCategory: String? = null
) : Serializable
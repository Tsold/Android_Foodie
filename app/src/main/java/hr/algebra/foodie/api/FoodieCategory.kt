package hr.algebra.foodie.api

import com.google.gson.annotations.SerializedName

class FoodieCategory (

    @SerializedName("idCategory") val idCategory : Int,
    @SerializedName("strCategory") val strCategory : String,
    @SerializedName("strCategoryThumb") val strCategoryThumb : String,
    @SerializedName("strCategoryDescription") val strCategoryDescription : String
)
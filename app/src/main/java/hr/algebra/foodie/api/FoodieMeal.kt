package hr.algebra.foodie.api

import com.google.gson.annotations.SerializedName

class FoodieMeal (

    @SerializedName("strMeal") val strMeal : String,
    @SerializedName("strMealThumb") val strMealThumb : String,
    @SerializedName("idMeal") val idMeal : Int
)
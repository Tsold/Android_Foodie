package hr.algebra.foodie.api

import com.google.gson.annotations.SerializedName

class FoodieCategories (

    @SerializedName("categories") val categories : List<FoodieCategory>
)
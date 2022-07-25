package hr.algebra.foodie.api

import com.google.gson.annotations.SerializedName

class FoodieMeals (

    @SerializedName("meals") val meals : List<FoodieMeal>

    )
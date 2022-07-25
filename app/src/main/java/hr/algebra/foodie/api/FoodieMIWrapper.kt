package hr.algebra.foodie.api

import com.google.gson.annotations.SerializedName

class FoodieMIWrapper (

    @SerializedName("meals") val meals : List<FoodieMealDesc>

)
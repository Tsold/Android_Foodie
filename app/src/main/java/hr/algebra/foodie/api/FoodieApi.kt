package hr.algebra.foodie.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_URL = "https://www.themealdb.com/api/json/v1/1/"

interface FoodieApi {

    @GET("categories.php")
    fun getCategoryList(): Call<FoodieCategories>

    @GET("filter.php")
    fun getMealList(@Query("c") category: String): Call<FoodieMeals>

    @GET("lookup.php")
    fun getSpecificMealInfo(@Query("i") id: String): Call<FoodieMIWrapper>


}
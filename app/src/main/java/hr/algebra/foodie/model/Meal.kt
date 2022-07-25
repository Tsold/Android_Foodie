package hr.algebra.foodie.model



data class Meal(
    var _id: Long?,
    val strMeal : String,
    val strMealThumb : String,
    val idMeal : Int,
    val categoryName: String,
    var favorite: Boolean
)

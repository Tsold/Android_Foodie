package hr.algebra.foodie.frame_work

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.view.animation.AnimationUtils
import androidx.preference.PreferenceManager
import hr.algebra.foodie.api.TABLE_CATEGORY
import hr.algebra.foodie.api.TABLE_MEAL
import hr.algebra.foodie.api.TABLE_MEAL_INFO
import hr.algebra.foodie.dao.FoodieSqlHelper
import hr.algebra.foodie.factory.getFoodieRepository
import hr.algebra.foodie.model.Category
import hr.algebra.foodie.model.Meal
import hr.algebra.foodie.model.MealDesc

fun View.applyAnimation(resourceId: Int) =
    startAnimation(AnimationUtils.loadAnimation(context, resourceId))

inline fun<reified T: Activity> Context.startActivity(key: String, value: String)
        = startActivity(Intent(this, T::class.java).apply { putExtra(key, value) })

inline fun<reified T: Activity> Context.startActivity() = startActivity(Intent(this, T::class.java))

inline fun<reified T: BroadcastReceiver> Context.sendBroadcast() = sendBroadcast(Intent(this, T::class.java))

fun Context.setBooleanPreference(key: String, value: Boolean) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putBoolean(key, value)
        .apply()

fun Context.getBooleanPreference(key: String) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .getBoolean(key, false)

fun Context.isOnline() : Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    if (network != null) {
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        if (networkCapabilities != null) {
            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
    }
    return false
}

fun Context.fetchCategories() : MutableList<Category> {
    var repository: FoodieSqlHelper =  getFoodieRepository(this)
    val category = mutableListOf<Category>()
    val cursor = repository.query(TABLE_CATEGORY,null,null,null,null)
    if (cursor != null) {
        while(cursor.moveToNext()) {
            category.add(
                Category(
                    cursor.getLong(cursor.getColumnIndex(Category::_id.name)),
                    cursor.getString(cursor.getColumnIndex(Category::strCategory.name)),
                    cursor.getString(cursor.getColumnIndex(Category::strCategoryThumb.name)),
                    cursor.getString(cursor.getColumnIndex(Category::strCategoryDescription.name))
                )
            )
        }
        cursor.close()
    }
    cursor?.close()
    return category
}



fun Context.fetchMeals(category : String) : MutableList<Meal> {
    var repository: FoodieSqlHelper =  getFoodieRepository(this)
    val meal = mutableListOf<Meal>()
    val selectionArgs = arrayOf(category)
    val cursor = repository.query(TABLE_MEAL,null,"categoryName = ?", selectionArgs ,null)
    if (cursor != null) {
        while(cursor.moveToNext()) {
            meal.add(
                Meal(
                    cursor.getLong(cursor.getColumnIndex(Meal::_id.name)),
                    cursor.getString(cursor.getColumnIndex(Meal::strMeal.name)),
                    cursor.getString(cursor.getColumnIndex(Meal::strMealThumb.name)),
                    cursor.getInt(cursor.getColumnIndex(Meal::idMeal.name)),
                    cursor.getString(cursor.getColumnIndex(Meal::categoryName.name)),
                    cursor.getInt(cursor.getColumnIndex(Meal::favorite.name)) == 1
                )
            )
        }
        cursor.close()
    }
    cursor?.close()
    return meal
}

fun Context.fetchSingleMeal(idMeal: Int) : MutableList<Meal> {
    var repository: FoodieSqlHelper =  getFoodieRepository(this)
    val meal = mutableListOf<Meal>()
    val selectionArgs = arrayOf(idMeal.toString())
    val cursor = repository.query(TABLE_MEAL,null,"idMeal = ?", selectionArgs ,null)
    if (cursor != null) {
        while(cursor.moveToNext()) {
            meal.add(
                Meal(
                    cursor.getLong(cursor.getColumnIndex(Meal::_id.name)),
                    cursor.getString(cursor.getColumnIndex(Meal::strMeal.name)),
                    cursor.getString(cursor.getColumnIndex(Meal::strMealThumb.name)),
                    cursor.getInt(cursor.getColumnIndex(Meal::idMeal.name)),
                    cursor.getString(cursor.getColumnIndex(Meal::categoryName.name)),
                    cursor.getInt(cursor.getColumnIndex(Meal::favorite.name)) == 1
                )
            )
        }
        cursor.close()
    }
    cursor?.close()
    return meal
}



fun Context.fetchMealInfo(idMeal : Int) : MutableList<MealDesc> {
    var repository: FoodieSqlHelper =  getFoodieRepository(this)
    val mealDesc = mutableListOf<MealDesc>()
    val selectionArgs = arrayOf(idMeal.toString())
    val cursor = repository.query(TABLE_MEAL_INFO,null,"idMeal = ?", selectionArgs ,null)
    if (cursor != null) {
        while(cursor.moveToNext()) {
            mealDesc.add(
                MealDesc(
                    cursor.getLong(cursor.getColumnIndex(MealDesc::_id.name)),
                    cursor.getInt(cursor.getColumnIndex(MealDesc::idMeal.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeal.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strCategory.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strArea.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strInstructions.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMealThumb.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strYoutube.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient1.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient2.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient3.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient4.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient5.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient6.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient7.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient8.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient9.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient10.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient11.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient12.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient13.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient14.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient15.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient16.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient17.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient18.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient19.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strIngredient20.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure1.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure2.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure3.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure4.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure5.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure6.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure7.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure8.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure9.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure10.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure11.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure12.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure13.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure14.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure15.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure16.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure17.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure18.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure19.name)),
                    cursor.getString(cursor.getColumnIndex(MealDesc::strMeasure20.name))
                )
            )
        }
        cursor.close()
    }
    cursor?.close()
    return mealDesc
}

fun Context.updateMealInfoFavorite(favorite: Boolean,idMeal: Int) {
    var repository: FoodieSqlHelper =  getFoodieRepository(this)
    val selectionArgs = arrayOf(idMeal.toString())
    repository.update(TABLE_MEAL,
        ContentValues().apply {
        put(Meal::favorite.name, favorite)
    },"idMeal = ?",selectionArgs)

}




fun Context.fetchMealFavorites() : MutableList<Meal> {
    var repository: FoodieSqlHelper =  getFoodieRepository(this)
    val meal = mutableListOf<Meal>()
    val one = 1
    val selectionArgs = arrayOf(one.toString())
    val cursor = repository.query(TABLE_MEAL,null,"favorite = ?", selectionArgs ,null)
    if (cursor != null) {
        while(cursor.moveToNext()) {
            meal.add(
                Meal(
                    cursor.getLong(cursor.getColumnIndex(Meal::_id.name)),
                    cursor.getString(cursor.getColumnIndex(Meal::strMeal.name)),
                    cursor.getString(cursor.getColumnIndex(Meal::strMealThumb.name)),
                    cursor.getInt(cursor.getColumnIndex(Meal::idMeal.name)),
                    cursor.getString(cursor.getColumnIndex(Meal::categoryName.name)),
                    cursor.getInt(cursor.getColumnIndex(Meal::favorite.name)) == 1
                )
            )
        }
        cursor.close()
    }
    cursor?.close()
    return meal
}








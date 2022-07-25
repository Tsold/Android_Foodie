package hr.algebra.foodie.api

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import hr.algebra.foodie.FoodieReceiver
import hr.algebra.foodie.R
import hr.algebra.foodie.dao.FoodieSqlHelper
import hr.algebra.foodie.factory.getFoodieRepository
import hr.algebra.foodie.frame_work.sendBroadcast
import hr.algebra.foodie.handler.downloadImageAndStore
import hr.algebra.foodie.model.Category
import hr.algebra.foodie.model.Meal
import hr.algebra.foodie.model.MealDesc
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val TABLE_CATEGORY = "Category"
const val TABLE_MEAL = "Meal"
const val TABLE_MEAL_INFO= "MealInfo"

class FoodieFetcher(private val context: Context) {

    private val picturePath = "/storage/emulated/0/Android/data/hr.algebra.foodie/files/"
    private val jpg = ".jpg"


    private var repository: FoodieSqlHelper =  getFoodieRepository(context)

    private var foodieApi: FoodieApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        foodieApi = retrofit.create(FoodieApi::class.java)
    }

     fun fetchCategories() {
        val call = foodieApi.getCategoryList()
        call.enqueue(object : Callback<FoodieCategories> {

            override fun onFailure(call: Call<FoodieCategories>, t: Throwable) {
                Toast.makeText(context, context.getString(R.string.Error), Toast.LENGTH_SHORT)
                    .show()
            }
            override fun onResponse(
                call: Call<FoodieCategories>,
                response: Response<FoodieCategories>
            ) {
                response.body()?.let { populateCategories(it.categories) }

                for (arr in response.body()!!.categories!!) {
                    fetchMeals(arr.strCategory)
                }
            }

        })
    }

    fun fetchMeals(category: String) {
        val call = foodieApi.getMealList(category)
        call.enqueue(object : Callback<FoodieMeals> {

            override fun onFailure(call: Call<FoodieMeals>, t: Throwable) {
                Toast.makeText(context, context.getString(R.string.Error), Toast.LENGTH_SHORT)
                    .show()
            }
            override fun onResponse(
                call: Call<FoodieMeals>,
                response: Response<FoodieMeals>
            ) {
                response.body()?.let { populateMeals(it.meals,category) }
                for (arr in response.body()!!.meals!!) {
                fetchMealInfo(arr.idMeal)
                }

            }

        })
    }


    fun fetchMealInfo(idMeal: Int) {
        val call = foodieApi.getSpecificMealInfo(idMeal.toString())
        call.enqueue(object : Callback<FoodieMIWrapper> {


            override fun onFailure(call: Call<FoodieMIWrapper>, t: Throwable) {
                Toast.makeText(context, context.getString(R.string.Error), Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(
                call: Call<FoodieMIWrapper>,
                response: Response<FoodieMIWrapper>
            ) {
                response.body()?.let { populateMealInfo(it.meals) }
            }

        })
    }


    private fun populateCategories(foodieCategories: List<FoodieCategory>) {
        GlobalScope.launch {
            foodieCategories.forEach {
                val picturePath = downloadImageAndStore(context, it.strCategoryThumb, it.strCategory.replace(" ", "_"),320,200 )
                val values = ContentValues().apply {
                    put(Category::strCategory.name, it.strCategory)
                    put(Category::strCategoryDescription.name, it.strCategoryDescription)
                    put(Category::strCategoryThumb.name, picturePath ?: "")
                }
                repository.insert(TABLE_CATEGORY,values)

            }
            context.sendBroadcast<FoodieReceiver>()
        }
    }

    private fun populateMeals(foodieMeals: List<FoodieMeal>, category: String) {
        GlobalScope.launch {
            foodieMeals.forEach {
                val picturePath = downloadImageAndStore(context, it.strMealThumb, it.strMeal.replace(" ", "_"),500,500 )
                val values = ContentValues().apply {
                    put(Meal::strMeal.name, it.strMeal)
                    put(Meal::strMealThumb.name, picturePath ?: "")
                    put(Meal::idMeal.name, it.idMeal)
                    put(Meal::categoryName.name, category)
                    put(Meal::favorite.name,  false)
                }
                repository.insert(TABLE_MEAL,values)
            }
        }
    }


    private fun populateMealInfo(foodieMeals: List<FoodieMealDesc>) {
        GlobalScope.launch {
            foodieMeals.forEach {
                val picturePath = picturePath+it.strMeal.replace(" ", "_")+jpg
                val values = ContentValues().apply {
                    put(MealDesc::idMeal.name, it.idMeal)
                    put(MealDesc::strMeal.name, it.strMeal)
                    put(MealDesc::strCategory.name, it.strCategory)
                    put(MealDesc::strArea.name, it.strArea)
                    put(MealDesc::strInstructions.name, it.strInstructions)
                    put(MealDesc::strMealThumb.name, picturePath)
                    put(MealDesc::strYoutube.name,  it.strYoutube)
                    put(MealDesc::strIngredient1.name,  it.strIngredient1)
                    put(MealDesc::strIngredient2.name,  it.strIngredient2)
                    put(MealDesc::strIngredient3.name,  it.strIngredient3)
                    put(MealDesc::strIngredient4.name,  it.strIngredient4)
                    put(MealDesc::strIngredient5.name,  it.strIngredient5)
                    put(MealDesc::strIngredient6.name,  it.strIngredient6)
                    put(MealDesc::strIngredient7.name,  it.strIngredient7)
                    put(MealDesc::strIngredient8.name,  it.strIngredient8)
                    put(MealDesc::strIngredient9.name,  it.strIngredient9)
                    put(MealDesc::strIngredient10.name,  it.strIngredient10)
                    put(MealDesc::strIngredient11.name,  it.strIngredient11)
                    put(MealDesc::strIngredient12.name,  it.strIngredient12)
                    put(MealDesc::strIngredient13.name,  it.strIngredient13)
                    put(MealDesc::strIngredient14.name,  it.strIngredient14)
                    put(MealDesc::strIngredient15.name,  it.strIngredient15)
                    put(MealDesc::strIngredient16.name,  it.strIngredient16)
                    put(MealDesc::strIngredient17.name,  it.strIngredient17)
                    put(MealDesc::strIngredient18.name,  it.strIngredient18)
                    put(MealDesc::strIngredient19.name,  it.strIngredient19)
                    put(MealDesc::strIngredient20.name,  it.strIngredient20)
                    put(MealDesc::strMeasure1.name,  it.strMeasure1)
                    put(MealDesc::strMeasure2.name,  it.strMeasure2)
                    put(MealDesc::strMeasure3.name,  it.strMeasure3)
                    put(MealDesc::strMeasure4.name,  it.strMeasure4)
                    put(MealDesc::strMeasure5.name,  it.strMeasure5)
                    put(MealDesc::strMeasure6.name,  it.strMeasure6)
                    put(MealDesc::strMeasure7.name,  it.strMeasure7)
                    put(MealDesc::strMeasure8.name,  it.strMeasure8)
                    put(MealDesc::strMeasure9.name,  it.strMeasure9)
                    put(MealDesc::strMeasure10.name,  it.strMeasure10)
                    put(MealDesc::strMeasure11.name,  it.strMeasure11)
                    put(MealDesc::strMeasure12.name,  it.strMeasure12)
                    put(MealDesc::strMeasure13.name,  it.strMeasure13)
                    put(MealDesc::strMeasure14.name,  it.strMeasure14)
                    put(MealDesc::strMeasure15.name,  it.strMeasure15)
                    put(MealDesc::strMeasure16.name,  it.strMeasure16)
                    put(MealDesc::strMeasure17.name,  it.strMeasure17)
                    put(MealDesc::strMeasure18.name,  it.strMeasure18)
                    put(MealDesc::strMeasure19.name,  it.strMeasure19)
                    put(MealDesc::strMeasure20.name,  it.strMeasure20)

                }
                repository.insert(TABLE_MEAL_INFO,values)
            }
        }
    }
}


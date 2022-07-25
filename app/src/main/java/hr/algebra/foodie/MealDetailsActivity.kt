package hr.algebra.foodie



import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import hr.algebra.foodie.frame_work.fetchMealInfo
import hr.algebra.foodie.frame_work.fetchSingleMeal
import hr.algebra.foodie.frame_work.updateMealInfoFavorite
import hr.algebra.foodie.model.Meal
import hr.algebra.foodie.model.MealDesc
import kotlinx.android.synthetic.main.activity_meal_details.*
import java.io.File


const val ID = "ID"

class MealDetailsActivity : AppCompatActivity() {

    lateinit var  mealInfoList: MutableList<MealDesc>
    lateinit var  singleMeal: MutableList<Meal>
    var youtubeLink = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_details)
        supportActionBar?.hide()

        var idMeal = intent.getStringExtra(ID)
        singleMeal = this.fetchSingleMeal(idMeal!!.toInt())
        mealInfoList = this.fetchMealInfo(idMeal!!.toInt())
        init(mealInfoList)

        imgToolbarBtnBack.setOnClickListener {
        finish()

        }


        btnYoutube.setOnClickListener {
            val uri = Uri.parse(youtubeLink)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }


        imgToolbarBtnFav.setOnClickListener{
            var favorite = singleMeal[0].favorite
            favorite = !favorite

            this.updateMealInfoFavorite(favorite,singleMeal[0].idMeal)
            finish()
            overridePendingTransition(0, 0)
            startActivity(intent)
            overridePendingTransition(0, 0)

        }

    }

   private fun init(mealInfoList: MutableList<MealDesc>){

       Picasso.get()
           .load(File(mealInfoList[0].strMealThumb))
           .error(R.drawable.beef)
           .into(imgItem)

        tvCategory.text = mealInfoList[0].strMeal

        var ingredient = "${mealInfoList[0].strIngredient1}    ${mealInfoList[0].strMeasure1}\n" +
                "${mealInfoList[0].strIngredient2}      ${mealInfoList[0].strMeasure2}\n" +
                "${mealInfoList[0].strIngredient3}     ${mealInfoList[0].strMeasure3}\n" +
                "${mealInfoList[0].strIngredient4}      ${mealInfoList[0].strMeasure4}\n" +
                "${mealInfoList[0].strIngredient5}      ${mealInfoList[0].strMeasure5}\n" +
                "${mealInfoList[0].strIngredient6}      ${mealInfoList[0].strMeasure6}\n" +
                "${mealInfoList[0].strIngredient7}      ${mealInfoList[0].strMeasure7}\n" +
                "${mealInfoList[0].strIngredient8}      ${mealInfoList[0].strMeasure8}\n" +
                "${mealInfoList[0].strIngredient9}      ${mealInfoList[0].strMeasure9}\n" +
                "${mealInfoList[0].strIngredient10}      ${mealInfoList[0].strMeasure10}\n" +
                "${mealInfoList[0].strIngredient11}      ${mealInfoList[0].strMeasure11}\n" +
                "${mealInfoList[0].strIngredient12}      ${mealInfoList[0].strMeasure12}\n" +
                "${mealInfoList[0].strIngredient13}      ${mealInfoList[0].strMeasure13}\n" +
                "${mealInfoList[0].strIngredient14}      ${mealInfoList[0].strMeasure14}\n" +
                "${mealInfoList[0].strIngredient15}      ${mealInfoList[0].strMeasure15}\n" +
                "${mealInfoList[0].strIngredient16}      ${mealInfoList[0].strMeasure16}\n" +
                "${mealInfoList[0].strIngredient17}      ${mealInfoList[0].strMeasure17}\n" +
                "${mealInfoList[0].strIngredient18}      ${mealInfoList[0].strMeasure18}\n" +
                "${mealInfoList[0].strIngredient19}      ${mealInfoList[0].strMeasure19}\n" +
                "${mealInfoList[0].strIngredient20}      ${mealInfoList[0].strMeasure20}\n"

        tvIngredients.text = ingredient
        tvInstructions.text = mealInfoList[0].strInstructions

       imgToolbarBtnFav.setImageResource(if (singleMeal[0].favorite) R.drawable.ic_is_favorite else R.drawable.ic_not_favorite)

       if (mealInfoList[0].strYoutube != null){
           youtubeLink = mealInfoList[0].strYoutube.toString()
       }else{
           btnYoutube.visibility = View.GONE
       }


    }




}
package hr.algebra.foodie.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import hr.algebra.foodie.model.Category
import hr.algebra.foodie.model.Meal
import hr.algebra.foodie.model.MealDesc

private const val DB_NAME = "Foodie.db"
private const val DB_VERSION = 1
private const val TABLE_Category = "Category"
private const val TABLE_Meal = "Meal"
private const val TABLE_MealInfo = "MealInfo"
private val CREATE_TABLE_Category = "create table $TABLE_Category( " +
        "${Category::_id.name} integer primary key autoincrement, " +
        "${Category::strCategory.name} text not null, " +
        "${Category::strCategoryThumb.name} text not null, " +
        "${Category::strCategoryDescription.name} text not null " +
        ")"
private val CREATE_TABLE_Meal = "create table $TABLE_Meal( " +
        "${Meal::_id.name} integer primary key autoincrement, " +
        "${Meal::strMeal.name} text not null, " +
        "${Meal::strMealThumb.name} text not null, " +
        "${Meal::idMeal.name} integer not null, " +
        "${Meal::categoryName.name} text not null, " +
        "${Meal::favorite.name} integer not null" +
        ")"
private val CREATE_TABLE_mealInfo = "create table $TABLE_MealInfo( " +
        "${MealDesc::_id.name} integer primary key autoincrement, " +
        "${MealDesc::idMeal.name} integer not null UNIQUE, " +
        "${MealDesc::strMeal.name} text not null, " +
        "${MealDesc::strCategory.name} text  , " +
        "${MealDesc::strArea.name} text  , " +
        "${MealDesc::strInstructions.name} text  , " +
        "${MealDesc::strMealThumb.name} text  , " +
        "${MealDesc::strYoutube.name} text  , " +
        "${MealDesc::strIngredient1.name} text  , " +
        "${MealDesc::strIngredient2.name} text  , " +
        "${MealDesc::strIngredient3.name} text  , " +
        "${MealDesc::strIngredient4.name} text  , " +
        "${MealDesc::strIngredient5.name} text  , " +
        "${MealDesc::strIngredient6.name} text  , " +
        "${MealDesc::strIngredient7.name} text  , " +
        "${MealDesc::strIngredient8.name} text  , " +
        "${MealDesc::strIngredient9.name} text  , " +
        "${MealDesc::strIngredient10.name} text  , " +
        "${MealDesc::strIngredient11.name} text  , " +
        "${MealDesc::strIngredient12.name} text  , " +
        "${MealDesc::strIngredient13.name} text  , " +
        "${MealDesc::strIngredient14.name} text  , " +
        "${MealDesc::strIngredient15.name} text  , " +
        "${MealDesc::strIngredient16.name} text  , " +
        "${MealDesc::strIngredient17.name} text  , " +
        "${MealDesc::strIngredient18.name} text  , " +
        "${MealDesc::strIngredient19.name} text  , " +
        "${MealDesc::strIngredient20.name} text  , " +
        "${MealDesc::strMeasure1.name} text  , " +
        "${MealDesc::strMeasure2.name} text  , " +
        "${MealDesc::strMeasure3.name} text  , " +
        "${MealDesc::strMeasure4.name} text  , " +
        "${MealDesc::strMeasure5.name} text  , " +
        "${MealDesc::strMeasure6.name} text  , " +
        "${MealDesc::strMeasure7.name} text  , " +
        "${MealDesc::strMeasure8.name} text  , " +
        "${MealDesc::strMeasure9.name} text  , " +
        "${MealDesc::strMeasure10.name} text  , " +
        "${MealDesc::strMeasure11.name} text  , " +
        "${MealDesc::strMeasure12.name} text  , " +
        "${MealDesc::strMeasure13.name} text  , " +
        "${MealDesc::strMeasure14.name} text  , " +
        "${MealDesc::strMeasure15.name} text  , " +
        "${MealDesc::strMeasure16.name} text  , " +
        "${MealDesc::strMeasure17.name} text  , " +
        "${MealDesc::strMeasure18.name} text  , " +
        "${MealDesc::strMeasure19.name} text  , " +
        "${MealDesc::strMeasure20.name} text   " +
        ")"




fun dropTable(table: String ) :String = "drop table $table"



class FoodieSqlHelper(context: Context?): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),
    FoodieRepository {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_Category)
        db.execSQL(CREATE_TABLE_Meal)
        db.execSQL(CREATE_TABLE_mealInfo)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(dropTable(TABLE_Category))
        db.execSQL(dropTable(TABLE_Meal))
        db.execSQL(dropTable(TABLE_MealInfo))

        onCreate(db)
    }


    override fun delete(table: String,selection: String?, selectionArgs: Array<String>?)
            = writableDatabase.delete(table, selection, selectionArgs)

    override fun insert(table: String, values: ContentValues?) = writableDatabase.insert(table, null, values)

    override fun query(
        table: String,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? = readableDatabase
        .query(table,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder

        )

    override fun update(
        table: String, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ) = writableDatabase.update(table, values, selection, selectionArgs)

}
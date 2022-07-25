package hr.algebra.foodie.dao

import android.content.ContentValues
import android.database.Cursor

interface FoodieRepository {


    fun delete(table: String,selection: String?, selectionArgs: Array<String>?): Int

    fun insert(table: String, values: ContentValues?): Long

    fun query(
        table: String, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor?

    fun update(
        table: String, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int

}
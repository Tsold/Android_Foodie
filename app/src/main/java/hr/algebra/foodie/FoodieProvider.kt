package hr.algebra.foodie

import android.content.*
import android.database.Cursor
import android.net.Uri
import hr.algebra.foodie.dao.FoodieSqlHelper
import hr.algebra.foodie.factory.getFoodieRepository
import hr.algebra.foodie.model.Category
import java.lang.IllegalArgumentException


private const val AUTHORITY = "hr.algebra.foodie.api.provider"
private const val PATH = "categories"
val FOODIE_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH")

val TABLE = "Category"

private const val CATEGORIES = 10
private const val CATEGORY_ID = 20

private val URI_MATCHER = with(UriMatcher(UriMatcher.NO_MATCH)) {
    addURI(AUTHORITY, PATH, CATEGORIES) //content://hr.algebra.nasa.api.provider/items
    addURI(AUTHORITY, "$PATH/#", CATEGORY_ID) //content://hr.algebra.nasa.api.provider/items/1
    this
}

private const val CONTENT_DIR_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + PATH
private const val CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + PATH


private lateinit var repository: FoodieSqlHelper

class FoodieProvider : ContentProvider() {

    private lateinit var repository: FoodieSqlHelper

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        when(URI_MATCHER.match(uri)) {
            CATEGORIES -> return repository.delete(TABLE,selection, selectionArgs)
            CATEGORY_ID -> {
                val id = uri.lastPathSegment // 1
                if (id != null) {
                    return repository.delete(TABLE,"${Category::_id.name} = ?", arrayOf(id))
                }
            }
        }
        throw IllegalArgumentException("Wrong URI")    }

    override fun getType(uri: Uri): String? {
        when(URI_MATCHER.match(uri)) {
            CATEGORIES -> return CONTENT_DIR_TYPE
            CATEGORY_ID -> return CONTENT_ITEM_TYPE
        }
        throw IllegalArgumentException("Wrong URI")
    }

    //hr.algebra.nasa.api.provider/items
    //hr.algebra.nasa.api.provider/items/22
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = repository.insert(TABLE,values)
        return ContentUris.withAppendedId(FOODIE_PROVIDER_CONTENT_URI, id)
    }

    override fun onCreate(): Boolean {
        repository = getFoodieRepository(context)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? = repository.query(TABLE, projection, selection, selectionArgs, sortOrder)


    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        when(URI_MATCHER.match(uri)){
            CATEGORIES -> return repository.update(TABLE,values, selection, selectionArgs)
            CATEGORY_ID -> {
                val id = uri.lastPathSegment
                if (id != null)
                {
                    return repository.update(TABLE, values,"${Category::_id.name}​= ?", arrayOf(id))
                }
            }
        }
        throw IllegalArgumentException("Wrong URI")
    }

}
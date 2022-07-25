package hr.algebra.foodie.factory

import android.content.Context
import hr.algebra.foodie.ItemFragment
import hr.algebra.foodie.api.FoodieFetcher
import hr.algebra.foodie.dao.FoodieSqlHelper

fun getFoodieRepository(context: Context?) = FoodieSqlHelper(context)


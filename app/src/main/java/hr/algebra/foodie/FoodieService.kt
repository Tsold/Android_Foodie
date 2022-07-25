

package hr.algebra.foodie

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.algebra.foodie.api.FoodieFetcher

private const val JOB_ID = 1

class FoodieService : JobIntentService() {


    override fun onHandleWork(intent: Intent) {
     FoodieFetcher(this).fetchCategories()
    }

    companion object {
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, FoodieService::class.java, JOB_ID, intent)
        }
    }
}
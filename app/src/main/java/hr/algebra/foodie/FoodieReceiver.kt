package hr.algebra.foodie

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.foodie.frame_work.setBooleanPreference
import hr.algebra.foodie.frame_work.startActivity

class FoodieReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.setBooleanPreference(DATA_IMPORTED, true)
        context.startActivity<HostActivity>()
    }
}
